package eu.mosov.steamtradehelper.model;

import javax.json.JsonObject;
import java.util.*;

public class Item implements Comparable<Item> {
  private String name;
  private Map<String, List<JsonObject>> properties;

  public Item(String name) {
    this.name = name;
    this.properties = new HashMap<>();
  }

  public String getName() {
    return name;
  }

  public List<JsonObject> getGroup(String group) {
    return properties.get(group.toLowerCase());
  }

  public void addProperty(String key, JsonObject value) {
    if (!this.hasGroup(key)) {
      properties.put(key, new ArrayList<>());
    }
    this.getGroup(key).add(value);
  }

  public void addProperty(String key, List<JsonObject> values) {
    if (!this.hasGroup(key)) {
      properties.put(key, new ArrayList<>());
    }
    this.getGroup(key).addAll(values);
  }

  /*-------Public utility methods------*/
  public boolean hasGroup(String group) {
    return properties.containsKey(group.toLowerCase());
  }

/*  public boolean hasOwnProperty(String group, String property) {
    if (!this.hasGroup(group)) return false;
    List<JsonObject> attr = this.getGroup(group);

    if (!attr.contains(p.toLowerCase())) return false;

    return true;
  }*/

  public boolean clearPropertyGroup(String group) {
    if (!this.hasGroup(group)) {
      return false;
    }

    properties.remove(group.toLowerCase());
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

  /*-------Getters-------*/
  //getter for
  public Map<String, List<JsonObject>> getProperties() {
    return Collections.unmodifiableMap(properties);
  }

  public int getPropertiesGroupsCount() {
    return properties.size();
  }
}
