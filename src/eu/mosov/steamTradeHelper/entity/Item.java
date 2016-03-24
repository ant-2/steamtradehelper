package eu.mosov.steamTradeHelper.entity;

public class Item {
	int id;
	String name;

	public Item(String name) {
		this.name = name;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Item)) return false;
		Item item = (Item) o;
		return name.equals(item.name);
	}

	public int hashCode() {
		return name.hashCode();
	}

	public String toString() {
		return name;
	}
}
