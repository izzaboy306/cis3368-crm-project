package main.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order", schema = "public", catalog = "salesfarce")
public class Order implements Serializable {
	private int orderId;
	private String title;
	private OrderStatus orderStatus;
	private OrderType orderType;
	private State state;

	public Order () {
	}

	public Order (int orderId, String title, OrderStatus orderStatus, OrderType orderType, State state) {
		this.orderId = orderId;
		this.title = title;
		this.orderStatus = orderStatus;
		this.orderType = orderType;
		this.state = state;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", nullable = false)
	public int getOrderId () {
		return orderId;
	}

	public void setOrderId (int orderId) {
		this.orderId = orderId;
	}

	@Basic
	@Column(name = "title", nullable = false, length = 20)
	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	@ManyToOne
	@JoinColumn(name = "order_status_id")
	public OrderStatus getOrderStatus () {
		return orderStatus;
	}

	public void setOrderStatus (OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@ManyToOne
	@JoinColumn(name = "order_type_id")
	public OrderType getOrderType () {
		return orderType;
	}

	public void setOrderType (OrderType orderType) {
		this.orderType = orderType;
	}

	@ManyToOne
	@JoinColumn(name = "state_id")
	public State getState () {
		return state;
	}

	public void setState (State state) {
		this.state = state;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Order order = (Order) o;

		if (orderId != order.orderId) return false;
		if (title != null ? !title.equals(order.title) : order.title != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = orderId;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}

	public String toString () {
		return title;
	}
}
