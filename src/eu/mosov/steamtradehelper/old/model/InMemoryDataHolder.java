package eu.mosov.steamtradehelper.old.model;

import eu.mosov.steamtradehelper.client.RestClient;

import javax.json.*;
import java.util.*;
import java.util.function.Function;

public class InMemoryDataHolder {
  private Map<String, JsonObject> mem;
  private RestClient client;
  private Date lastTimeUpdated;
  private int updateInterval;

  private static InMemoryDataHolder INSTANCE;

  public InMemoryDataHolder() {
    mem = new HashMap<>();
    client = new RestClient();
    lastTimeUpdated = new Date();
    updateInterval = 1000 * 60 * 60;    // updates data each hour
  }

  public static InMemoryDataHolder getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new InMemoryDataHolder();
    }
    return INSTANCE;
  }

  public JsonObject getResource(String uri) {
    if (!mem.containsKey(uri)) {
      mem.put(uri, client.getResourceAsType(uri, JsonObject.class));
    }
    return mem.get(uri);
  }

  public JsonObject getResource(String uri, Function<JsonObject, JsonObject> function) {
    if (!mem.containsKey(uri)) {
      JsonObject value = function.apply(client.getResourceAsType(uri, JsonObject.class));
      mem.put(uri, value);
    }
    return mem.get(uri);
  }

  public JsonObject updateAndGetResource(String uri) {
    JsonObject result = client.getResourceAsType(uri, JsonObject.class);
    mem.put(uri, result);
    return result;
  }

  /*----------private utility methods-----------*/
  private void dropRefreshTimer() {
    lastTimeUpdated.setTime(lastTimeUpdated.getTime() - updateInterval * 2);
  }

  private boolean isTimeToUpdate() {
    return System.currentTimeMillis() - lastTimeUpdated.getTime() > (updateInterval);
  }

  private Date setLastTimeUpdated() {
    lastTimeUpdated.setTime(System.currentTimeMillis());
    return lastTimeUpdated;
  }

  /*--------------Item ItemPostProcessor class---------------*/
  private static class ItemParser {

    private static Set<JsonObject> getTypes(JsonObject item) {
      Set<JsonObject> set = new HashSet<>();
      item.entrySet()
          .stream()
          .filter(e -> {
            JsonValue.ValueType type = e.getValue().getValueType();
            return type.equals(JsonValue.ValueType.OBJECT);
          })
          .map(e -> {
            return Json.createObjectBuilder()
                       .add(e.getKey(), e.getValue())
                       .build();
          })
          .forEach(set::add);

      return set;
    }

    private static JsonArray getKeysAsJsonArray(JsonObject json) {
      JsonArrayBuilder builder = Json.createArrayBuilder();

      json
          .entrySet()
          .stream()
          .forEach(e -> builder.add(e.getKey()));

      return builder.build();
    }
  }
}
