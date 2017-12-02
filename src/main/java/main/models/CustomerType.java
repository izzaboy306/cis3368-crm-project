package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer_type", schema = "public", catalog = "salesfarce")
public class CustomerType {
	private int customerTypeId;
	private String title;

	@Id
	@Column(name = "customer_type_id", nullable = false)
	public int getCustomerTypeId () {
		return customerTypeId;
	}

	public void setCustomerTypeId (int customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	@Basic
	@Column(name = "title", nullable = true, length = 40)
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
		CustomerType that = (CustomerType) o;
		return customerTypeId == that.customerTypeId &&
				Objects.equals(title, that.title);
	}

	@Override
	public int hashCode () {

		return Objects.hash(customerTypeId, title);
	}
}
