package eu.mosov.steamtradehelper.data;

import eu.mosov.steamtradehelper.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

@Repository
@Scope("singleton")
public class RawDataRepo {
  @Autowired private RestClient client;
  private Map<String, JsonObject> mem;

  public RawDataRepo() {
    mem = new HashMap<>();
  }

  private JsonObject _getAndSave(String id, String uri) {
    mem.put(id, client.getResourceAsType(uri, JsonObject.class));
    return mem.get(id);
  }

  public JsonObject getResource(String id, String uri, boolean isNeedUpdate) {
    if (isNeedUpdate || !mem.containsKey(id)) {
      _getAndSave(id, uri);
    }
    return mem.get(id);
  }
}
