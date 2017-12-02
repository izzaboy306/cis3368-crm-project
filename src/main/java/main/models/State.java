package main.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "state", schema = "public", catalog = "salesfarce")
public class State implements Serializable {
	private int stateId;
	private String title;
	private String abbreviation;
	private Country country;

	@ManyToOne
	@JoinColumn(name = "country_id")
	public Country getCountry () {
		return country;
	}

	public void setCountry (Country country) {
		this.country = country;
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
	@Column(name = "title", nullable = true, length = 40)
	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	@Basic
	@Column(name = "abbreviation", nullable = true, length = 2)
	public String getAbbreviation () {
		return abbreviation;
	}

	public void setAbbreviation (String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		State state = (State) o;
		return stateId == state.stateId &&
				Objects.equals(title, state.title) &&
				Objects.equals(abbreviation, state.abbreviation);
	}

	@Override
	public int hashCode () {

		return Objects.hash(stateId, title, abbreviation);
	}
}