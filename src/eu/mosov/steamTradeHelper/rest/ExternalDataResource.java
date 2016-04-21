package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.client.BackpackApiClient;
import eu.mosov.steamtradehelper.model.InMemoryDataRepository;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Provides direct access to data from backpack.tf site
 */
@Path("raw")
@Produces({"application/json", "application/javascript"})
public class ExternalDataResource extends SpringAwareResource {
  InMemoryDataRepository repo = new InMemoryDataRepository();
  BackpackApiClient client = new BackpackApiClient();

  @GET
  @Path("prices")
  public JsonObject getRawPrices() {
    return client.getPrices();
  }

  @GET
  @Path("curr")
  public JsonObject getRawCurrencies() {
    return client.getCurrencies();
  }
}
