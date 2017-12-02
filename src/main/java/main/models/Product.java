package main.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product", schema = "public", catalog = "salesfarce")
public class Product implements Serializable {
	private int productId;
	private String title;
	private int productStatusId;
	private int productTypeId;
	private double price;

	@Id
	@Column(name = "product_id", nullable = false)
	public int getProductId () {
		return productId;
	}

	public void setProductId (int productId) {
		this.productId = productId;
	}

	@Basic
	@Column(name = "title", nullable = false, length = 20)
	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	@Basic
	@Column(name = "product_status_id", nullable = false)
	public int getProductStatusId () {
		return productStatusId;
	}

	public void setProductStatusId (int productStatusId) {
		this.productStatusId = productStatusId;
	}

	@Basic
	@Column(name = "product_type_id", nullable = false)
	public int getProductTypeId () {
		return productTypeId;
	}

	public void setProductTypeId (int productTypeId) {
		this.productTypeId = productTypeId;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (productId != product.productId) return false;
		if (productStatusId != product.productStatusId) return false;
		if (productTypeId != product.productTypeId) return false;
		if (title != null ? !title.equals(product.title) : product.title != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = productId;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + productStatusId;
		result = 31 * result + productTypeId;
		return result;
	}
}
