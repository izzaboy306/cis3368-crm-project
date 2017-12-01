package main.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_status", schema = "public", catalog = "salesfarce")
public class ProductStatus implements Serializable {
	private int productStatusId;
	private String title;

	@Id
	@Column(name = "product_status_id", nullable = false)
	public int getProductStatusId () {
		return productStatusId;
	}

	public void setProductStatusId (int productStatusId) {
		this.productStatusId = productStatusId;
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

		ProductStatus that = (ProductStatus) o;

		if (productStatusId != that.productStatusId) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = productStatusId;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}
}
