package main.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "order", schema = "public", catalog = "salesfarce")
public class Order implements Serializable {
	private int orderId;
	private String title;
	private OrderStatus orderStatus;
	private User user;
	private Set<OrderNote> orderNotes;
	private double total;

	public Order () {
	}

	public Order (String title, OrderStatus orderStatus, User user) {
		this.title = title;
		this.orderStatus = orderStatus;
		this.user = user;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
	public Set<OrderNote> getOrderNotes () {
		return orderNotes;
	}

	public void setOrderNotes (Set<OrderNote> orderNotes) {
		this.orderNotes = orderNotes;
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
	@JoinColumn(name = "user_id")
	public User getUser () {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "order_status_id")
	public OrderStatus getOrderStatus () {
		return orderStatus;
	}

	public void setOrderStatus (OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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
