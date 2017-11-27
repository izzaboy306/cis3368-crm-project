package main;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order_status", schema = "public", catalog = "salesfarce")
public class OrderStatus {
	private int orderStatusId;
	private String title;
	private Set<Order> orders;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "orderStatus", cascade = CascadeType.ALL)
	public Set<Order> getOrders () {
		return orders;
	}

	public void setOrders (Set<Order> orders) {
		this.orders = orders;
	}

	@Id
	@Column(name = "order_status_id", nullable = false)
	public int getOrderStatusId () {
		return orderStatusId;
	}

	public void setOrderStatusId (int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	@Basic
	@Column(name = "title", nullable = false, length = 20)
	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrderStatus that = (OrderStatus) o;

		if (orderStatusId != that.orderStatusId) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = orderStatusId;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}
}
