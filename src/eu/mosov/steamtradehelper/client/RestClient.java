package eu.mosov.steamtradehelper.client;

import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Repository
public class RestClient {
  Client client;

  public RestClient() {
    client = ClientBuilder.newClient();
  }

  public JsonObject getResourceAsJson(String uri) {
    JsonObject result = null;
    try {
      result = client.target(uri).request().get().readEntity(JsonObject.class);
    } catch (Exception e) {
      throw new RuntimeException("Can't get resource: "+uri, e);
    }
    return result;
  }
}
