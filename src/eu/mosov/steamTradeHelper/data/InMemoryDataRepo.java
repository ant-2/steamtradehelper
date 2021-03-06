package eu.mosov.steamTradeHelper.data;

import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDataRepo {
  private Map<String, JsonObject> mem = new HashMap<>();

  public void put(String key, JsonObject value) {
    mem.put(key, value);
  }

  public JsonObject get(String key) {
    return mem.get(key);
  }
}
