package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.old.model.InMemoryDataHolder;

import javax.json.JsonObject;
import javax.ws.rs.*;

/**
 * Provides direct access to data from backpack.tf site
 */
@Path("raw")
@Produces({"application/json", "application/javascript"})
public class ExternalDataResource extends SpringAwareResource {
  InMemoryDataHolder repo = InMemoryDataHolder.getInstance();
  private static String API_KEY = "56e4698ddea9e91a45b9de12";
  private static String PRICES = "http://backpack.tf/api/IGetPrices/v4?key="+API_KEY;
  private static String CURRENCIES = "http://backpack.tf/api/IGetCurrencies/v1?key="+API_KEY;
  private static String MARKET = "http://backpack.tf/api/IGetMarketPrices/v1?key="+API_KEY;

  @GET
  @Path("prices")
  public JsonObject getItemPrices(@DefaultValue("false") @QueryParam("update") boolean update) {
    if (update) {
      return repo.updateAndGetResource(PRICES);
    }
    return repo.getResource(PRICES);
  }

  @GET
  @Path("curr")
  public JsonObject getCurrencies(@DefaultValue("false") @QueryParam("update") boolean update) {
    if (update) {
      return repo.updateAndGetResource(CURRENCIES);
    }
    return repo.getResource(CURRENCIES);
  }

  @GET
  @Path("market")
  public JsonObject getSteamMarketPrice(@DefaultValue("false") @QueryParam("update") boolean update) {
    if (update) {
      return repo.updateAndGetResource(MARKET);
    }
    return repo.getResource(MARKET);
  }
}
