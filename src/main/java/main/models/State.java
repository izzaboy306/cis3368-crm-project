package main.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class State implements Serializable {
	private int stateId;
	private String title;
	private String abbreviation;
	private Country country;
	private Set<Order> orders;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "state", cascade = CascadeType.ALL)
	public Set<Order> getOrders () {
		return orders;
	}

	public void setOrders (Set<Order> orders) {
		this.orders = orders;
	}

	@Id
	@Column(name = "state_id", nullable = false)
	public int getStateId () {
		return stateId;
	}

	public void setStateId (int stateId) {
		this.stateId = stateId;
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
	@Column(name = "abbreviation", nullable = false, length = 2)
	public String getAbbreviation () {
		return abbreviation;
	}

	public void setAbbreviation (String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = true)
	public Country getCountry () {
		return country;
	}

	public void setCountry (Country country) {
		this.country = country;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		State state = (State) o;

		if (stateId != state.stateId) return false;
		if (!title.equals(state.title)) return false;
		return abbreviation.equals(state.abbreviation) && country.equals(state.country);
	}

	@Override
	public int hashCode () {
		int result = stateId;
		result = 31 * result + title.hashCode();
		result = 31 * result + abbreviation.hashCode();
		result = 31 * result + country.hashCode();
		return result;
	}
}
