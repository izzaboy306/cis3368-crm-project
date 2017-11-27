package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable {

	@FXML
	private TextField txtProduct;

	@FXML
	private TextField txtAmount;

	@FXML
	private Button btnAddCustomerAndInvoice;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Called to initialize a controller after its root element has been
	 * completely processed.
	 *
	 * @param location  The location used to resolve relative paths for the root object, or
	 *                  <tt>null</tt> if the location is not known.
	 * @param resources The resources used to localize the root object, or <tt>null</tt> if
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Brown");
		userRepository.save(user);
	}

	public void doPrintCustomers(ActionEvent actionEvent) {
		Iterable<User> c = userRepository.findAll();
		c.forEach(System.out::println);
	}

	public void doAddCustomer(ActionEvent actionEvent) {
		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		User customer = new User(firstName, lastName);
		userRepository.save(customer);
	}
}
