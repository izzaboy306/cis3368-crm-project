package main.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order_note", schema = "public", catalog = "salesfarce")
public class OrderNote implements Serializable {
	private int orderNoteId;
	private String title;
	private String message;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	private Order order;

	public OrderNote () {
	}

	public OrderNote (int orderNoteId, String title, String message, Order order) {
		this.orderNoteId = orderNoteId;
		this.title = title;
		this.message = message;
		this.order = order;
	}

	@ManyToOne
	@JoinColumn(name = "order_id")
	public Order getOrder () {
		return order;
	}

	public void setOrder (Order order) {
		this.order = order;
	}

	@Basic
	@Column(name = "title", nullable = false)
	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Column(name = "created_at", insertable = false)
	public Date getCreatedAt () { return createdAt; }

	public void setCreatedAt (Date createdAt) { this.createdAt = createdAt; };

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrderNote orderNote = (OrderNote) o;

		if (orderNoteId != orderNote.orderNoteId) return false;
		if (message != null ? !message.equals(orderNote.message) : orderNote.message != null) return false;
		return true;
	}

	@Override
	public int hashCode () {
		int result = orderNoteId;
		result = 31 * result + (message != null ? message.hashCode() : 0);
		return result;
	}

	@Override
	public String toString () {
		return getTitle();
	}
}
