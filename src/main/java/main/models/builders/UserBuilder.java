package main.models.builders;

import main.models.Order;
import main.models.User;
import main.models.UserType;

import java.util.Set;

public class UserBuilder {
	private String firstName;
	private String lastName;
	private UserType userType;
	private Set<Order> orders;

	public UserBuilder setFirstName (String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserBuilder setLastName (String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserBuilder setUserType (UserType userType) {
		this.userType = userType;
		return this;
	}

	public UserBuilder setOrders (Set<Order> orders) {
		this.orders = orders;
		return this;
	}

	public User createUser () {
		return new User(firstName, lastName, userType, orders);
	}
}