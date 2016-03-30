package eu.mosov.steamTradeHelper.entity;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.GenerationType.AUTO;

@Entity @Access(FIELD)
public class Item {
	@GeneratedValue(strategy = AUTO)
	@Id Integer id;

	String name;
	String description;

	protected Item() {
	}

	public Item(String name) {
		this.name = name;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Item)) return false;

		Item e = (Item) o;
/*		if (id != null && e.id != null) {
			return id.equals(e.id);
		}
		return name.equals(e.getName());*/
		return name.equals(e.getName());
	}

	public int hashCode() {
/*		int result = id != null ? id : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;*/
		return name.hashCode();
	}

	public String toString() {
		return name+ " : "+description ;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
