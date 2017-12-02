package main.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Customer {
	private int customerId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private String addressOne;
	private String addressTwo;
	private String city;
	private String postalZipCode;

	@Id
	@Column(name = "customer_id", nullable = false)
	public int getCustomerId () {
		return customerId;
	}

	public void setCustomerId (int customerId) {
		this.customerId = customerId;
	}

	@Basic
	@Column(name = "first_name", nullable = true, length = 40)
	public String getFirstName () {
		return firstName;
	}

	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}

	@Basic
	@Column(name = "last_name", nullable = true, length = 40)
	public String getLastName () {
		return lastName;
	}

	public void setLastName (String lastName) {
		this.lastName = lastName;
	}

	@Basic
	@Column(name = "phone_number", nullable = true, length = 10)
	public String getPhoneNumber () {
		return phoneNumber;
	}

	public void setPhoneNumber (String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Basic
	@Column(name = "email_address", nullable = true, length = 255)
	public String getEmailAddress () {
		return emailAddress;
	}

	public void setEmailAddress (String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Basic
	@Column(name = "address_one", nullable = true, length = 80)
	public String getAddressOne () {
		return addressOne;
	}

	public void setAddressOne (String addressOne) {
		this.addressOne = addressOne;
	}

	@Basic
	@Column(name = "address_two", nullable = true, length = 80)
	public String getAddressTwo () {
		return addressTwo;
	}

	public void setAddressTwo (String addressTwo) {
		this.addressTwo = addressTwo;
	}

	@Basic
	@Column(name = "city", nullable = true, length = 40)
	public String getCity () {
		return city;
	}

	public void setCity (String city) {
		this.city = city;
	}

	@Basic
	@Column(name = "postal_zip_code", nullable = true, length = 15)
	public String getPostalZipCode () {
		return postalZipCode;
	}

	public void setPostalZipCode (String postalZipCode) {
		this.postalZipCode = postalZipCode;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer) o;
		return customerId == customer.customerId &&
				Objects.equals(firstName, customer.firstName) &&
				Objects.equals(lastName, customer.lastName) &&
				Objects.equals(phoneNumber, customer.phoneNumber) &&
				Objects.equals(emailAddress, customer.emailAddress) &&
				Objects.equals(addressOne, customer.addressOne) &&
				Objects.equals(addressTwo, customer.addressTwo) &&
				Objects.equals(city, customer.city) &&
				Objects.equals(postalZipCode, customer.postalZipCode);
	}

	@Override
	public int hashCode () {

		return Objects.hash(customerId, firstName, lastName, phoneNumber, emailAddress, addressOne, addressTwo, city, postalZipCode);
	}
}
