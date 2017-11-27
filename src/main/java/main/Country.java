package main;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Country {
	private int countryId;
	private String title;
	private Set<State> states;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "country", cascade = CascadeType.ALL)
	public Set<State> getStates () {
		return states;
	}

	public void setStates (Set<State> states) {
		this.states = states;
	}

	@Id
	@Column(name = "country_id", nullable = false)
	public int getCountryId () {
		return countryId;
	}

	public void setCountryId (int countryId) {
		this.countryId = countryId;
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

		Country country = (Country) o;

		if (countryId != country.countryId) return false;
		if (title != null ? !title.equals(country.title) : country.title != null) return false;

		return true;
	}

	@Override
	public int hashCode () {
		int result = countryId;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}
}
