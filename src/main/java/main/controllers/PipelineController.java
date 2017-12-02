package main.controllers;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.*;
import main.models.Order;
import main.models.OrderStatus;
import main.models.User;
import main.models.UserType;
import main.repositories.OrderRepository;
import main.repositories.UserRepository;
import main.services.ActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PipelineController implements FxmlController {
	@FXML private ListView<Order> prospectList;
	@FXML private ListView<Order> infoList;
	@FXML private ListView<Order> proposalList;
	@FXML private ListView<Order> negotiationList;
	@FXML private ListView<Order> closingList;
	@FXML private TextField txtOrderTitle;
	@FXML private TextField txtAssignedEmployee;
	@FXML private ComboBox<OrderStatus> menuOrderStatus;
	@FXML private ComboBox<User> menuAssignedEmployee;
	@FXML private Label labelAssignedEmployee;
	@FXML private Button btnSaveDetails;
	@FXML private Button btnEditProducts;
	@FXML private Button btnEditCustomer;

	@Autowired private UserRepository userRepository;
	@Autowired private OrderRepository orderRepository;
	@Autowired private List<OrderStatus> orderStatuses;
	@Autowired private List<UserType> userTypes;
	@Autowired private ActiveUserService activeUserService;

	private final DataFormat custom_order_format = new DataFormat("A sales order for the salesman.");
	private Order selectedOrder;
	private ListView selectedList;
	private ListView[] listViewList;

	/**
	 * Called by the {@link FXMLLoader} to initialize a controller after its
	 * root element has been completely processed. This means all of the
	 * controller's {@link FXML} elements will be injected, and they can be used
	 * to wire up the GUI in ways that couldn't be accomplished using pure FXML,
	 * e.g. attaching property listeners.
	 */
	@Override
	public void initialize () {
		listViewList = new ListView[]{prospectList, infoList, proposalList, negotiationList, closingList};
		menuOrderStatus.setItems(FXCollections.observableList(orderStatuses));

		if (activeUserService.getActiveUser().getUserType().equals(userTypes.get(1))) {
			labelAssignedEmployee.setVisible(true);
			menuAssignedEmployee.setVisible(true);
		}

		updatePipeline();
		setEvents();
	}

	protected void updatePipeline () {
		User activeUser = activeUserService.getActiveUser();

		prospectList.getItems().clear();
		infoList.getItems().clear();
		proposalList.getItems().clear();
		negotiationList.getItems().clear();
		closingList.getItems().clear();

		if (activeUser.getUserType().equals(userTypes.get(0))) {
			prospectList.setItems(FXCollections.observableList(orderRepository
					.findByOrderStatusAndUser(orderStatuses.get(0), activeUser)));
			infoList.setItems(FXCollections.observableList(orderRepository
					.findByOrderStatusAndUser(orderStatuses.get(1), activeUser)));
			proposalList.setItems(FXCollections.observableList(orderRepository
					.findByOrderStatusAndUser(orderStatuses.get(2), activeUser)));
			negotiationList.setItems(FXCollections.observableList(orderRepository
					.findByOrderStatusAndUser(orderStatuses.get(3), activeUser)));
			closingList.setItems(FXCollections.observableList(orderRepository
					.findByOrderStatusAndUser(orderStatuses.get(4), activeUser)));

		} else {
			prospectList.setItems(FXCollections.observableList(orderRepository.findByOrderStatus(orderStatuses.get(0))));
			infoList.setItems(FXCollections.observableList(orderRepository.findByOrderStatus(orderStatuses.get(1))));
			proposalList.setItems(FXCollections.observableList(orderRepository.findByOrderStatus(orderStatuses.get(2))));
			negotiationList.setItems(FXCollections.observableList(orderRepository.findByOrderStatus(orderStatuses.get(3))));
			closingList.setItems(FXCollections.observableList(orderRepository.findByOrderStatus(orderStatuses.get(4))));
		}
	}

	private void setEvents () {
		EventHandler handleGrab = event -> {
			selectedList = (ListView) event.getSource();
			if (!selectedList.getSelectionModel().isEmpty()) {
				Dragboard db = selectedList.startDragAndDrop(TransferMode.MOVE);
				selectedOrder = (Order) selectedList.getSelectionModel().getSelectedItem();
				ClipboardContent cc = new ClipboardContent();
				cc.put(custom_order_format, selectedOrder);
				db.setContent(cc);
				event.consume();
			}
		};
		EventHandler<DragEvent> dragOver = event -> {
			selectedList = (ListView) event.getSource();
			event.acceptTransferModes(TransferMode.MOVE);
			event.consume();
		};
		EventHandler<DragEvent> handleDrop = event -> {
			selectedList = (ListView) event.getSource();
			updateOrderStatus(selectedList.getId());
			orderRepository.save(selectedOrder);
			selectedOrder = null;
			event.setDropCompleted(true);
			updatePipeline();

		};
		EventHandler<DragEvent> dragExitedHandler = (event) -> {
			selectedList = (ListView) event.getSource();
			event.consume();
		};
		EventHandler<MouseEvent> handleMouseClick = event -> {
			selectedList = (ListView) event.getSource();
			if (!selectedList.getSelectionModel().isEmpty()) {
				selectedOrder = (Order) selectedList.getSelectionModel().getSelectedItem();

				txtOrderTitle.setText(selectedOrder.getTitle());
				txtAssignedEmployee.setText(selectedOrder.getUser().retrieveFullName());
				menuOrderStatus.getSelectionModel().select(selectedOrder.getOrderStatus());

				txtOrderTitle.setDisable(false);
				txtAssignedEmployee.setDisable(false);
				menuOrderStatus.setDisable(false);

				btnEditProducts.setDisable(false);
				btnEditCustomer.setDisable(false);
				btnSaveDetails.setDisable(false);

				if (activeUserService.getActiveUser().getUserType().equals(userTypes.get(1))) {
					menuAssignedEmployee.setDisable(false);
					menuAssignedEmployee.setItems(FXCollections.observableList(userRepository.findAllByUserTypeEquals(userTypes.get(0))));
					menuAssignedEmployee.getSelectionModel().select(selectedOrder.getUser());
				}
				event.consume();
			}
		};

		for (ListView listView : listViewList) {
			listView.setOnDragDetected(handleGrab);
			listView.setOnDragOver(dragOver);
			listView.setOnDragDropped(handleDrop);
			listView.setOnDragExited(dragExitedHandler);
			listView.setOnMouseClicked(handleMouseClick);
		}
	}

	private void updateOrderStatus (String id) {
		TableNames tableName = TableNames.valueOf(id.toUpperCase());
		switch (tableName) {
			case PROSPECTLIST:
				selectedOrder.setOrderStatus(orderStatuses.get(0));
				break;
			case INFOLIST:

				selectedOrder.setOrderStatus(orderStatuses.get(1));
				break;
			case PROPOSALLIST:
				selectedOrder.setOrderStatus(orderStatuses.get(2));
				break;
			case NEGOTIATIONLIST:
				selectedOrder.setOrderStatus(orderStatuses.get(3));
				break;
			case CLOSINGLIST:
				selectedOrder.setOrderStatus(orderStatuses.get(4));
				break;
		}
	}

	public enum TableNames {
		PROSPECTLIST, INFOLIST, PROPOSALLIST, NEGOTIATIONLIST, CLOSINGLIST
	}

	public void loadEditProducts () {

	}

	public void loadEditCustomer () {

	}

	public void saveDetails () {
		selectedOrder.setOrderStatus(menuOrderStatus.getSelectionModel().getSelectedItem());
		orderRepository.save(selectedOrder);
		selectedOrder = null;

		txtOrderTitle.clear();
		txtAssignedEmployee.clear();

		txtOrderTitle.setDisable(true);
		txtAssignedEmployee.setDisable(true);
		menuOrderStatus.setDisable(true);
		menuAssignedEmployee.setDisable(true);
		btnEditProducts.setDisable(true);
		btnEditCustomer.setDisable(true);
		btnSaveDetails.setDisable(true);

		menuOrderStatus.getSelectionModel().clearSelection();
		menuAssignedEmployee.getSelectionModel().clearSelection();

		updatePipeline();
	}
}
