package main.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import main.models.User;
import main.repositories.UserRepository;
import main.view.FxmlView;
import main.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LoginController implements FxmlController {
	private final StageManager stageManager;

	@FXML private ComboBox<User> menuEmployeeList;

	@Autowired private UserRepository userRepository;

	@Autowired
	@Lazy
	public LoginController (StageManager stageManager) {
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

	public void loginAsUser () {
		stageManager.switchScene(FxmlView.SALESPIPELINE);
	}

	public void loginAsManager () {
		stageManager.switchScene(FxmlView.MANAGERPIPELINE);
	}
}
