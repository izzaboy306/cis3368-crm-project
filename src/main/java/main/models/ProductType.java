package main.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_type", schema = "public", catalog = "salesfarce")
public class ProductType implements Serializable {
	private int productTypeId;
	private String title;

	@Id
	@Column(name = "product_type_id", nullable = false)
	public int getProductTypeId () {
		return productTypeId;
	}

	public void setProductTypeId (int productTypeId) {
		this.productTypeId = productTypeId;
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

		ProductType that = (ProductType) o;

		if (productTypeId != that.productTypeId) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = productTypeId;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}
}
