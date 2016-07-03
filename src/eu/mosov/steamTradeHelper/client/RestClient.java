package eu.mosov.steamTradeHelper.client;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class RestClient {
  private Client client;

  private RestClient() {
    client = ClientBuilder.newClient();
  }

  public <T> T getResourceAsType(String uri, Class<T> type) {
    T result;
    try {
      result = client.target(uri).request().get().readEntity(type);
    } catch (ProcessingException e) {
      throw new RuntimeException("Failure in attempt to access external data resource. Can't convert content of resource {"+uri+"} to type of "+type.getName(), e);
    } catch (IllegalStateException e) {
      throw new RuntimeException("Failure in attempt to access external data resource. Can't get resource {"+uri+"}, some how state of the objects in request processing chain is not in valid state.", e);
    } catch (Exception e) {
      throw new RuntimeException("Failure in attempt to access external data resource. Can't get resource: "+uri, e);
    }
    return result;
  }
}
