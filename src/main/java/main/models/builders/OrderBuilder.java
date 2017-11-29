package main.models.builders;

import main.models.Order;
import main.models.OrderStatus;
import main.models.OrderType;
import main.models.State;

public class OrderBuilder {
	private int orderId;
	private String title;
	private OrderStatus orderStatus;
	private OrderType orderType;
	private State state;

	public OrderBuilder setOrderId (int orderId) {
		this.orderId = orderId;
		return this;
	}

	public OrderBuilder setTitle (String title) {
		this.title = title;
		return this;
	}

	public OrderBuilder setOrderStatus (OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
		return this;
	}

	public OrderBuilder setOrderType (OrderType orderType) {
		this.orderType = orderType;
		return this;
	}

	public OrderBuilder setState (State state) {
		this.state = state;
		return this;
	}

	public Order createOrder () {
		return new Order(orderId, title, orderStatus, orderType, state);
	}
}