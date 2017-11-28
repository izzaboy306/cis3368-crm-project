package main.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.models.User;
import main.models.UserStatus;
import main.repositories.UserRepository;
import main.repositories.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class MainController implements Initializable {
	private Parent root;
	private ConfigurableApplicationContext springContext;

	@FXML private TextField txtFirstName;
	@FXML private TextField txtLastName;
	@FXML private TextField txtStatusTitle;
	@FXML private ComboBox<String> userStatusMenu;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserStatusRepository userStatusRepository;

	/**
	 * Called to initialize a controller after its root element has been
	 * completely processed.
	 *
	 * @param location  The location used to resolve relative paths for the root object, or
	 *                  <tt>null</tt> if the location is not known.
	 * @param resources The resources used to localize the root object, or <tt>null</tt> if
	 */
	@Override
	public void initialize (URL location, ResourceBundle resources) {
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

	public void changeScene(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/second.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		root = fxmlLoader.load();
	}
}
