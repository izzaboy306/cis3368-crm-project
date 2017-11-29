package main.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import main.models.Order;
import main.models.OrderStatus;
import main.models.builders.OrderBuilder;
import main.repositories.OrderRepository;
import main.repositories.OrderStatusRepository;
import main.view.FxmlView;
import main.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesMainController implements FxmlController {
	private final StageManager stageManager;

	@FXML private TextField txtNewLeadField;
	@FXML private ListView<Order> prospectList;
	@FXML private ListView<Order> infoList;
	@FXML private ListView<Order> proposalList;
	@FXML private ListView<Order> negotiationList;
	@FXML private ListView<Order> closingList;

	@Autowired private OrderRepository orderRepository;
	@Autowired private OrderStatusRepository orderStatusRepository;
	@Autowired private List<OrderStatus> orderStatuses;

	private DataFormat df = new DataFormat("an order");

	@Autowired
	@Lazy
	public SalesMainController (StageManager stageManager) {
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
		updatePipeline();
		initializeProspectListListeners();
	}

	public void goBack () {
		stageManager.switchScene(FxmlView.MAIN);
	}

	public void loadNewProspectScreen () {
		orderRepository.save(new OrderBuilder().setTitle(txtNewLeadField.getText()).setOrderStatus(orderStatuses.get(0)).createOrder());
		txtNewLeadField.clear();
		updatePipeline();
	}

	private void updatePipeline () {
		prospectList.getItems().clear();
		infoList.getItems().clear();
		proposalList.getItems().clear();
		negotiationList.getItems().clear();
		closingList.getItems().clear();

		prospectList.setItems(FXCollections.observableList(orderRepository.findByOrderStatus(orderStatuses.get(0))));
		infoList.setItems(FXCollections.observableList(orderRepository.findByOrderStatus(orderStatuses.get(1))));
		proposalList.setItems(FXCollections.observableList(orderRepository.findByOrderStatus(orderStatuses.get(2))));
		negotiationList.setItems(FXCollections.observableList(orderRepository.findByOrderStatus(orderStatuses.get(3))));
		closingList.setItems(FXCollections.observableList(orderRepository.findByOrderStatus(orderStatuses.get(4))));
	}

	private void initializeProspectListListeners () {
		final Order[] selectedOrder = new Order[1];

		prospectList.setOnDragDetected(event -> {
			// Dummy action to get the drag event initiated.
			Dragboard db = prospectList.startDragAndDrop(TransferMode.MOVE);
			ClipboardContent cc = new ClipboardContent();
			cc.put(df, prospectList.getSelectionModel().getSelectedItem());
			//cc.putString(prospectList.getSelectionModel().getSelectedItem().getTitle());
			db.setContent(cc);

			// This is what really matters.
			//selectedOrder[0] = prospectList.getSelectionModel().getSelectedItem();
		});

		infoList.setOnDragEntered(dragEvent -> {
			infoList.setBlendMode(BlendMode.DIFFERENCE);
		});

		infoList.setOnDragExited(dragEvent -> {
			infoList.setBlendMode(null);
		});

		infoList.setOnDragOver(dragEvent -> {
			dragEvent.acceptTransferModes(TransferMode.MOVE);
		});

		infoList.setOnDragDropped(dragEvent -> {
			orderRepository.removeByOrderId(selectedOrder[0].getOrderId());
			Order t = selectedOrder[0];
			t.setOrderStatus(orderStatusRepository.findByOrderStatusId(2));
			orderRepository.save(t);
			dragEvent.setDropCompleted(true);
			updatePipeline();
		});

		// Prosposal List
		proposalList.setOnDragEntered(dragEvent -> {
			proposalList.setBlendMode(BlendMode.DIFFERENCE);
		});

		proposalList.setOnDragExited(dragEvent -> {
			proposalList.setBlendMode(null);
		});

		proposalList.setOnDragOver(dragEvent -> {
			dragEvent.acceptTransferModes(TransferMode.MOVE);
		});

		proposalList.setOnDragDropped(dragEvent -> {
			orderRepository.removeByOrderId(selectedOrder[0].getOrderId());
			orderRepository.save(selectedOrder[0]);
			dragEvent.setDropCompleted(true);
			updatePipeline();
		});

		// Negotiation List
		negotiationList.setOnDragEntered(dragEvent -> {
			negotiationList.setBlendMode(BlendMode.DIFFERENCE);
		});

		negotiationList.setOnDragExited(dragEvent -> {
			negotiationList.setBlendMode(null);
		});

		negotiationList.setOnDragOver(dragEvent -> {
			dragEvent.acceptTransferModes(TransferMode.MOVE);
		});

		negotiationList.setOnDragDropped(dragEvent -> {
			orderRepository.removeByOrderId(selectedOrder[0].getOrderId());
			orderRepository.save(selectedOrder[0]);
			dragEvent.setDropCompleted(true);
			updatePipeline();
		});

		// Closing List
		closingList.setOnDragEntered(dragEvent -> {
			closingList.setBlendMode(BlendMode.DIFFERENCE);
		});

		closingList.setOnDragExited(dragEvent -> {
			closingList.setBlendMode(null);
		});

		closingList.setOnDragOver(dragEvent -> {
			dragEvent.acceptTransferModes(TransferMode.MOVE);
		});

		closingList.setOnDragDropped(dragEvent -> {
			orderRepository.removeByOrderId(selectedOrder[0].getOrderId());
			orderRepository.save(selectedOrder[0]);
			dragEvent.setDropCompleted(true);
			updatePipeline();
		});
	}
}
