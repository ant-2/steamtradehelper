package eu.mosov.steamtradehelper.model;

import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Item {
  public String name;
  public Map<String, Set<JsonObject>> map;

  public Item(String name) {
    this.name = name;
    this.map = new HashMap<>();
  }
}
