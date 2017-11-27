package main;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order_type", schema = "public", catalog = "salesfarce")
public class OrderType {
	private int orderTypeId;
	private String title;
	private Set<Order> orders;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "orderType", cascade = CascadeType.ALL)
	public Set<Order> getOrders () {
		return orders;
	}

	public void setOrders (Set<Order> orders) {
		this.orders = orders;
	}

	@Id
	@Column(name = "order_type_id", nullable = false)
	public int getOrderTypeId () {
		return orderTypeId;
	}

	public void setOrderTypeId (int orderTypeId) {
		this.orderTypeId = orderTypeId;
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

		OrderType orderType = (OrderType) o;

		if (orderTypeId != orderType.orderTypeId) return false;
		if (title != null ? !title.equals(orderType.title) : orderType.title != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = orderTypeId;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}
}
