package eu.mosov.steamtradehelper.model;

import java.util.*;

public class Item implements Comparable<Item> {
  private String name;
  private Map<String, List<String>> properties;
  public int propertiesGroupsCount;

  public Item(String name) {
    this.name = name;
    this.properties = new HashMap<>();
    propertiesGroupsCount = 0;
  }

  public String getName() {
    return name;
  }

  public void addProperty(String group, String... attr) {
    String gr = group.toLowerCase();
    if (!properties.containsKey(gr)) {
      properties.put(gr, new ArrayList<>());
    }

    if (attr.length != 0) {
      Collections.addAll(properties.get(gr), attr);
    }

    propertiesGroupsCount = properties.size();
  }

  public void clearPropertyGroup(String group) {
    properties.remove(group.toLowerCase());
  }

  public boolean hasOwnProperty(String group, String... property) {
    String gr = group.toLowerCase();
    if (!properties.containsKey(gr)) return false;

    List<String> attr = properties.get(gr);

    for (String p : property) {
      if(!attr.contains(p.toLowerCase()))  return false;
    }
    return true;
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
}
