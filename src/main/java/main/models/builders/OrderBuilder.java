package main.models.builders;

import main.models.Order;
import main.models.OrderStatus;
import main.models.User;

public class OrderBuilder {
	private String title;
	private OrderStatus orderStatus;
	private User user;

	public OrderBuilder setTitle (String title) {
		this.title = title;
		return this;
	}

	public OrderBuilder setOrderStatus (OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
		return this;
	}

	public OrderBuilder setUser (User user) {
		this.user = user;
		return this;
	}

	public Order createOrder () {
		return new Order(title, orderStatus, user);
	}
}