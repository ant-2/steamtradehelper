package eu.mosov.steamtradehelper.model;

import java.util.ArrayList;
import java.util.List;

public class NewItem implements Comparable<NewItem> {
  private String name;
  public List<Price> priceIndexes;

  public NewItem(String name) {
    this.name = name;
    this.priceIndexes = new ArrayList<>();
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NewItem)) return false;

    NewItem item = (NewItem) o;
    return name.equals(item.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public int compareTo(NewItem o) {
    return this.name.compareTo(o.name);
  }

  public String getName() {
    return name;
  }
}
