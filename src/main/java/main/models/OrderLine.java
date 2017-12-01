package main.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_line", schema = "public", catalog = "salesfarce")
@IdClass(OrderLinePK.class)
public class OrderLine implements Serializable {
	private int orderId;
	private int productId;

	@Id
	@Column(name = "order_id", nullable = false)
	public int getOrderId () {
		return orderId;
	}

	public void setOrderId (int orderId) {
		this.orderId = orderId;
	}

	@Id
	@Column(name = "product_id", nullable = false)
	public int getProductId () {
		return productId;
	}

	public void setProductId (int productId) {
		this.productId = productId;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrderLine orderLine = (OrderLine) o;

		if (orderId != orderLine.orderId) return false;
		if (productId != orderLine.productId) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = orderId;
		result = 31 * result + productId;
		return result;
	}
}
