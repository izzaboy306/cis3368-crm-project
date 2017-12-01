package main.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.models.OrderStatus;
import main.models.User;
import main.models.builders.OrderBuilder;
import main.repositories.OrderRepository;
import main.repositories.UserRepository;
import main.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagerPipelineViewController implements FxmlController {
	private final StageManager stageManager;

	@FXML private TextField txtNewLeadField;
	@FXML private ComboBox<User> menuEmployeeList;
	@FXML private ManagerPipelineController managerPipelineController;

	@Autowired private UserRepository userRepository;
	@Autowired private OrderRepository orderRepository;
	@Autowired private List<OrderStatus> orderStatuses;

	@Autowired
	@Lazy
	public ManagerPipelineViewController (StageManager stageManager) {
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
		menuEmployeeList.setItems(FXCollections.observableList(userRepository.findAll()));
	}

	public void createNewProspect () {
		orderRepository.save(new OrderBuilder().setTitle(txtNewLeadField.getText())
				.setOrderStatus(orderStatuses.get(0))
				.setUser(menuEmployeeList.getSelectionModel().getSelectedItem())
				.createOrder());
		txtNewLeadField.clear();
		menuEmployeeList.getSelectionModel().clearSelection();
		managerPipelineController.updatePipeline();
	}
}
