package main.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import main.models.OrderNote;
import main.models.builders.OrderNoteBuilder;
import main.repositories.OrderNotesRepository;
import main.services.ActiveOrderService;
import main.view.FxmlView;
import main.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class OrderNotesController implements FxmlController {
	private final StageManager stageManager;
	private ListView<OrderNote> selectedList;
	private OrderNote selectedNote;

	@FXML private ListView<OrderNote> orderNoteList;
	@FXML private TextArea orderNoteTextArea;
	@FXML private TextField txtNoteLabel;
	@FXML private Label labelTimestamp;

	@Autowired private OrderNotesRepository orderNotesRepository;
	@Autowired private ActiveOrderService activeOrderService;

	@Autowired
	@Lazy
	public OrderNotesController (StageManager stageManager) {
		this.stageManager = stageManager;
	}

	/**
	 * Called by the {@link FXMLLoader} to initialize a controller after its
	 * root element has been completely processed. This means all of the
	 * controller's {@link FXML} elements will be injected, and they can be used
	 * to wire up the GUI in ways that couldn't be accomplished using pure FXML,
	 * e.g. attaching property listeners.
	 */
	@Override
	public void initialize () {
		updateOrders();
		setEvents();
	}

	private void updateOrders () {
		orderNoteList.getItems().clear();
		orderNoteList.setItems(FXCollections.observableList(
				orderNotesRepository.findAllByOrder(activeOrderService.getActiveOrder())));
		orderNoteList.setCellFactory(lv -> {
			ListCell<OrderNote> cell = new ListCell<>();
			ContextMenu contextMenu = new ContextMenu();

			MenuItem deleteNote = new MenuItem();
			deleteNote.textProperty().bind(Bindings.format("Delete Note", cell.itemProperty()));
			deleteNote.setOnAction(event -> {
				System.out.println("Note Deleted");
			});

			contextMenu.getItems().addAll(deleteNote);
			StringBinding stringBinding = new StringBinding() {
				{
					super.bind(cell.itemProperty().asString());
				}

				@Override
				protected String computeValue () {
					if (cell.itemProperty().getValue() == null) {
						return "";
					}
					return cell.itemProperty().getValue().toString();
				}
			};
			cell.textProperty().bind(stringBinding);
			cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
				if (isNowEmpty) {
					cell.setContextMenu(null);
				} else {
					cell.setContextMenu(contextMenu);
				}
			});
			return cell;
		});
	}

	private void setEvents () {
		orderNoteList.setOnMouseClicked(event -> {
			selectedList = (ListView) event.getSource();
			if (!selectedList.getSelectionModel().isEmpty()) {
				selectedNote = selectedList.getSelectionModel().getSelectedItem();
				txtNoteLabel.setText(selectedNote.getTitle());
				labelTimestamp.setText(selectedNote.getCreatedAt().toString());
				orderNoteTextArea.setText(selectedNote.getMessage());
			}
			event.consume();
		});
		orderNoteTextArea.setOnMouseExited(event -> {
			if (selectedNote != null) {
				selectedNote.setMessage(orderNoteTextArea.getText());
				selectedNote.setTitle(txtNoteLabel.getText());
				orderNotesRepository.save(selectedNote);
				updateOrders();
			}
			event.consume();
		});
		txtNoteLabel.setOnMouseExited(event -> {
			if (selectedNote != null) {
				selectedNote.setMessage(orderNoteTextArea.getText());
				selectedNote.setTitle(txtNoteLabel.getText());
				orderNotesRepository.save(selectedNote);
				updateOrders();
			}
			event.consume();
		});
	}

	public void addNote () {
		OrderNote newNote = new OrderNoteBuilder().setTitle("New Note").setOrder(activeOrderService.getActiveOrder()).createOrderNote();
		orderNotesRepository.save(newNote);
		updateOrders();
	}

	public void saveNotes () {
		activeOrderService.setActiveOrder(null);
		stageManager.switchScene(FxmlView.SALESPIPELINE);
	}
}
