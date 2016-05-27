package eu.mosov.steamtradehelper.model;

import java.util.ArrayList;
import java.util.List;

public class Item implements Comparable<Item> {
  private String name;
  public String quality;
  public String tradable;
  public String craftable;
  public List<Price> priceIndexes;

  public Item(String name) {
    this.name = name;
    this.priceIndexes = new ArrayList<>();
  }

  /*------Override------*/
  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Item)) return false;

    Item item = (Item) o;
    return name.equals(item.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public int compareTo(Item o) {
    return this.name.compareTo(o.name);
  }

  public String getName() {
    return name;
  }
}
