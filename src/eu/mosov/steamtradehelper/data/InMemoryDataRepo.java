package eu.mosov.steamtradehelper.data;

import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDataRepo {
  private Map<String, JsonObject> mem = new HashMap<>();

  public void putResource(String key, JsonObject value) {
    mem.put(key, value);
  }

  public JsonObject getResource(String key) {
    return mem.get(key);
  }
}
