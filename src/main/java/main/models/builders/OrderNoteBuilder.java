package main.models.builders;

import main.models.Order;
import main.models.OrderNote;

public class OrderNoteBuilder {
	private int orderNoteId;
	private String title;
	private String message;
	private Order order;

	public OrderNoteBuilder setOrderNoteId (int orderNoteId) {
		this.orderNoteId = orderNoteId;
		return this;
	}

	public OrderNoteBuilder setTitle (String title) {
		this.title = title;
		return this;
	}

	public OrderNoteBuilder setMessage (String message) {
		this.message = message;
		return this;
	}

	public OrderNoteBuilder setOrder (Order order) {
		this.order = order;
		return this;
	}

	public OrderNote createOrderNote () {
		return new OrderNote(orderNoteId, title, message, order);
	}
}