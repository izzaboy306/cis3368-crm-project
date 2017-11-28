package main.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.models.User;
import main.models.UserStatus;
import main.repositories.UserRepository;
import main.repositories.UserStatusRepository;
import main.view.FxmlView;
import main.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class MainController implements FxmlController {
	private final StageManager stageManager;

	@FXML private TextField txtFirstName;
	@FXML private TextField txtLastName;
	@FXML private TextField txtStatusTitle;
	@FXML private ComboBox<String> userStatusMenu;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserStatusRepository userStatusRepository;

	@Autowired
	@Lazy
	public MainController(StageManager stageManager) {
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
		loadUserStatuses();
	}

	public void doPrintUsers () {
		System.out.println("User List:");
		Iterable<User> c = userRepository.findAll();
		c.forEach(System.out::println);

		System.out.println("User Status List:");
		Iterable<UserStatus> us = userStatusRepository.findAll();
		us.forEach(System.out::println);
	}

	public void doAddUser () {
		userRepository.save(new User(txtFirstName.getText(), txtLastName.getText()));
		txtFirstName.clear();
		txtLastName.clear();
	}

	public void doAddStatus () {
		userStatusRepository.save(new UserStatus((txtStatusTitle.getText())));
		txtStatusTitle.clear();
		loadUserStatuses();
	}

	private void loadUserStatuses() {
		userStatusMenu.getItems().clear();
		userStatusMenu.setItems(FXCollections.observableList(StreamSupport
				.stream(userStatusRepository.findAll().spliterator(), true)
				.map(UserStatus::getTitle).collect(Collectors.toList())));
	}

	public void goToSecond() {
		stageManager.switchScene(FxmlView.SECOND);
	}

	public void goToThird() {
		stageManager.switchScene(FxmlView.THIRD);
	}
}
