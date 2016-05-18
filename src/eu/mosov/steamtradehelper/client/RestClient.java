package eu.mosov.steamtradehelper.client;

import org.springframework.stereotype.Service;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Service
public class RestClient {
  Client client;

  public RestClient() {
    client = ClientBuilder.newClient();
  }

  public <T> T getResourceAsType(String uri, Class<T> type) {
    T result = null;
    try {
      result = client.target(uri).request().get().readEntity(type);
    } catch (ProcessingException e) {
      throw new RuntimeException("Can't convert content of resource {"+uri+"} to type of "+type.getName(), e);
    } catch (IllegalStateException e) {
      throw new RuntimeException("Can't get resource {"+uri+"}, some how state of the objects in request processing chain is not in valid state.", e);
    } catch (Exception e) {
      throw new RuntimeException("Can't get resource: "+uri, e);
    }
    return result;
  }
}
