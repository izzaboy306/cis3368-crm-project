package main.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_type", schema = "public", catalog = "salesfarce")
public class UserType {
	private int userTypeId;
	private String title;
	private Set<User> users;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userType", cascade = CascadeType.ALL)
	public Set<User> getUsers () {
		return users;
	}

	public void setUsers (Set<User> users) {
		this.users = users;
	}

	@Id
	@Column(name = "user_type_id", nullable = false)
	public int getUserTypeId () {
		return userTypeId;
	}

	public void setUserTypeId (int userTypeId) {
		this.userTypeId = userTypeId;
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

		UserType userType = (UserType) o;

		if (userTypeId != userType.userTypeId) return false;
		if (title != null ? !title.equals(userType.title) : userType.title != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = userTypeId;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}
}
