package main;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_status", schema = "public", catalog = "salesfarce")
public class UserStatus {
	private int userStatusId;
	private String title;
	private Set<User> users;

	public UserStatus () {}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userStatus", cascade = CascadeType.ALL)
	public Set<User> getUsers () {
		return users;
	}


	public void setUsers (Set<User> users) {
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_status_id", nullable = false)
	public int getUserStatusId () {
		return userStatusId;
	}

	public void setUserStatusId (int userStatusId) {
		this.userStatusId = userStatusId;
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

		UserStatus that = (UserStatus) o;

		if (userStatusId != that.userStatusId) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = userStatusId;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}
}
