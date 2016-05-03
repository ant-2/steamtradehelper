package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.client.RestClient;

import javax.json.JsonObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
      mem.put(uri, client.getResourceAsJson(uri));
    }
    return mem.get(uri);
  }

  public JsonObject getResource(String uri, Function<JsonObject, JsonObject> function) {
    if (!mem.containsKey(uri)) {
      JsonObject value = function.apply(client.getResourceAsJson(uri));
      mem.put(uri, value);
    }
    return mem.get(uri);
  }

  public JsonObject updateAndGetResource(String uri) {
    JsonObject result = client.getResourceAsJson(uri);
    mem.put(uri, result);
    return result;
  }

  // private utility methods
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
}
