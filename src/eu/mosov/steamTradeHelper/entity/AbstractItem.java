package eu.mosov.steamTradeHelper.entity;

/**
 * Parent of all items
 * */
public abstract class AbstractItem implements Item{

	String name;

	public AbstractItem(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AbstractItem)) return false;
		AbstractItem item = (AbstractItem) o;
		return name.equals(item.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
