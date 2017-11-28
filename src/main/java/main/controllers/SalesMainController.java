package main.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.models.Order;
import main.repositories.OrderRepository;
import main.view.FxmlView;
import main.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SalesMainController implements FxmlController {
	private final StageManager stageManager;

	@FXML private VBox prospectvbox;
	@FXML private VBox infovbox;
	@FXML private VBox proposalvbox;
	@FXML private VBox negotiationvbox;
	@FXML private VBox closingvbox;
	@FXML private TextField txtNewLeadField;
	@FXML private ListView<String> prospectList;
	@FXML private ListView<String> infoList;
	@FXML private ListView<String> proposalList;
	@FXML private ListView<String> negoatiationList;
	@FXML private ListView<String> closingList;

	@Autowired private OrderRepository orderRepository;

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
	}

	public void goBack () {
		stageManager.switchScene(FxmlView.MAIN);
	}

	public void loadNewProspectScreen () {
		orderRepository.save(new Order(txtNewLeadField.getText()));
		txtNewLeadField.clear();
		updatePipeline();
	}

	private void updatePipeline () {
		prospectList.getItems().clear();
		prospectList.setItems(FXCollections.observableList(StreamSupport
				.stream(orderRepository.findAll().spliterator(), true)
				.map(Order::getTitle).collect(Collectors.toList())));
	}
}
