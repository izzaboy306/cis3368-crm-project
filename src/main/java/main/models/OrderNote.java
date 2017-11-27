package main.models;

import javax.persistence.*;

@Entity
@Table(name = "order_note", schema = "public", catalog = "salesfarce")
public class OrderNote {
	private int orderNoteId;
	private String message;
	private Integer orderId;

	@Id
	@Column(name = "order_note_id", nullable = false)
	public int getOrderNoteId () {
		return orderNoteId;
	}

	public void setOrderNoteId (int orderNoteId) {
		this.orderNoteId = orderNoteId;
	}

	@Basic
	@Column(name = "message", nullable = true, length = -1)
	public String getMessage () {
		return message;
	}

	public void setMessage (String message) {
		this.message = message;
	}

	@Basic
	@Column(name = "order_id", nullable = true)
	public Integer getOrderId () {
		return orderId;
	}

	public void setOrderId (Integer orderId) {
		this.orderId = orderId;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrderNote orderNote = (OrderNote) o;

		if (orderNoteId != orderNote.orderNoteId) return false;
		if (message != null ? !message.equals(orderNote.message) : orderNote.message != null) return false;
		if (orderId != null ? !orderId.equals(orderNote.orderId) : orderNote.orderId != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = orderNoteId;
		result = 31 * result + (message != null ? message.hashCode() : 0);
		result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
		return result;
	}
}
