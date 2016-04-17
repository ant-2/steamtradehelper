package eu.mosov.steamtradehelper.model.entity;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;

import static javax.persistence.AccessType.FIELD;

@Entity
@Access(FIELD)
public class Quality {
	@Id String quality;
	int gameId;

	protected Quality() {}	// constructor for Hibernate

	public Quality(String name) {
		this.quality = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Quality)) return false;

		Quality quality = (Quality) o;

		return this.quality != null && this.quality.equals(quality.quality);
	}

	@Override
	public int hashCode() {
		return quality != null ? quality.hashCode() : 0;
	}
}
