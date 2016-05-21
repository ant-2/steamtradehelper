package eu.mosov.steamtradehelper.model;

import javax.json.JsonObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {
  public String name;
  public Map<String, List<JsonObject>> attributes;

  public Item(String name) {
    this.name = name;
    this.attributes = new HashMap<>();
  }

  public String getName() {
    return name;
  }

  public Map<String, List<JsonObject>> getAttributes() {
    return attributes;
  }

  public boolean hasOwnProperty(String group, String property) {
    if (!attributes.containsKey(group)) return false;

    return false;
  }
}
