package eu.mosov.steamtradehelper.rest;

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

  @GET
  @Path("parsed/curr")
  public JsonObject getCurrencies() {
    return null;
  }

  @GET
  @Path("parsed/prices")
  public JsonObject getPrices() {
    return null;
  }

  @GET
  @Path("full/prices")
  public JsonObject getFullPricesResponse() {
    return null;
  }

  @GET
  @Path("full/curr")
  public JsonObject getFullCurrenciesResponse() {
    return null;
  }
}
