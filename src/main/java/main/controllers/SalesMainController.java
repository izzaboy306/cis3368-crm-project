package main.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import main.models.OrderStatus;
import main.models.User;
import main.models.builders.OrderBuilder;
import main.repositories.OrderRepository;
import main.repositories.UserRepository;
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
	@FXML private ComboBox<User> menuEmployeeList;
	@FXML private MenuItem exitMenuItem;
	@FXML private MenuItem homeMenuItem;

	@Autowired private UserRepository userRepository;
	@Autowired private OrderRepository orderRepository;
	@Autowired private List<OrderStatus> orderStatuses;

	@FXML private SalesPipelineController salesPipelineController;

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
		loadEmployeeList();
		initializeMenuBarListeners();
	}

	private void initializeMenuBarListeners () {
		homeMenuItem.setOnAction(event -> stageManager.switchScene(FxmlView.MAIN));
		exitMenuItem.setOnAction(event -> Platform.exit());
	}

	public void createNewProspect () {
		orderRepository.save(new OrderBuilder().setTitle(txtNewLeadField.getText())
				.setOrderStatus(orderStatuses.get(0))
				.setUser(menuEmployeeList.getSelectionModel().getSelectedItem())
				.createOrder());
		txtNewLeadField.clear();
		menuEmployeeList.getSelectionModel().clearSelection();
		salesPipelineController.updatePipeline();
	}

	private void loadEmployeeList () {
		menuEmployeeList.setItems(FXCollections.observableList(userRepository.findAll()));
	}
}
