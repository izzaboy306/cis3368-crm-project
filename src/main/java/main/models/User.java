package main.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user", schema = "public", catalog = "salesfarce")
public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private UserStatus userStatus;
	private UserType userType;
	private Set<Order> orders;

	public User () {
	}

	public User (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	public Set<Order> getOrders () {
		return orders;
	}

	public void setOrders (Set<Order> orders) {
		this.orders = orders;
	}

	@ManyToOne
	@JoinColumn(name = "user_status_id")
	public UserStatus getUserStatus () {
		return userStatus;
	}

	public void setUserStatus (UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@ManyToOne
	@JoinColumn(name = "user_type_id")
	public UserType getUserType () {
		return userType;
	}

	public void setUserType (UserType userType) {
		this.userType = userType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	public int getUserId () {
		return userId;
	}

	public void setUserId (int userId) {
		this.userId = userId;
	}

	@Basic
	@Column(name = "first_name", nullable = false, length = 20)
	public String getFirstName () {
		return firstName;
	}

	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}

	@Basic
	@Column(name = "last_name", nullable = false, length = 20)
	public String getLastName () {
		return lastName;
	}

	public void setLastName (String lastName) {
		this.lastName = lastName;
	}

	public String retrieveFullName() {
		return firstName + " " + lastName;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (userId != user.userId) return false;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = userId;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		return result;
	}

	@Override
	public String toString () {
		return retrieveFullName();
	}
}
