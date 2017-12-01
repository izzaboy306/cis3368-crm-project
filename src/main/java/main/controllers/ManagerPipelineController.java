package main.controllers;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import main.models.Order;
import main.models.OrderStatus;
import main.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagerPipelineController implements FxmlController {
	@FXML private ListView<Order> prospectList;
	@FXML private ListView<Order> infoList;
	@FXML private ListView<Order> proposalList;
	@FXML private ListView<Order> negotiationList;
	@FXML private ListView<Order> closingList;

	@Autowired private OrderRepository orderRepository;
	@Autowired private List<OrderStatus> orderStatuses;

	private final DataFormat custom_order_format = new DataFormat("A sales order for the manager.");
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
		updatePipeline();
		setDragEvents();
	}

	protected void updatePipeline () {
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

	private void setDragEvents () {
		EventHandler handleGrab = (event) -> {
			selectedList = (ListView) event.getSource();
			Dragboard db = selectedList.startDragAndDrop(TransferMode.MOVE);
			selectedOrder = (Order) selectedList.getSelectionModel().getSelectedItem();
			ClipboardContent cc = new ClipboardContent();
			cc.put(custom_order_format, selectedOrder);
			db.setContent(cc);
			event.consume();
		};
		EventHandler<DragEvent> dragOver = (event) -> {
			selectedList = (ListView) event.getSource();
			event.acceptTransferModes(TransferMode.MOVE);
			event.consume();
		};
		EventHandler<DragEvent> handleDrop = (event) -> {
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

		for (ListView listView : listViewList) {
			listView.setOnDragDetected(handleGrab);
			listView.setOnDragOver(dragOver);
			listView.setOnDragDropped(handleDrop);
			listView.setOnDragExited(dragExitedHandler);
		}
	}

	private void updateOrderStatus (String id) {
		// Re-write this to use the orderStatuses bean rather than Enums
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
		PROSPECTLIST, INFOLIST, PROPOSALLIST, NEGOTIATIONLIST, CLOSINGLIST;
	}

	public void printOrderDetails () {
		Order selectedOrder = prospectList.getSelectionModel().getSelectedItem();
		System.out.printf("\nOrder Title: %s\nAssigned Employee: %s", selectedOrder.getTitle(), selectedOrder.getUser().retrieveFullName());
	}
}
