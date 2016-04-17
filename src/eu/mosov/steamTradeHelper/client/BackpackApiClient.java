package eu.mosov.steamtradehelper.client;

import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Makes requests to backpack.tf API
 */
@Repository
public class BackpackApiClient {
  String CURRENCY_URI = "http://backpack.tf/api/IGetCurrencies/v1/?key=56e4698ddea9e91a45b9de12";
  String PRICES_URI = "http://backpack.tf/api/IGetPrices/v4/?key=56e4698ddea9e91a45b9de12";

  Client client;

  public BackpackApiClient() {
    client = ClientBuilder.newClient();
  }

  public JsonObject getCurrencies() {
    return _getCurrenciesAsJson();
  }

  public JsonObject getPrices() {
    return _getPricesAsJson();
  }

  /* private methods for remove code duplication */
  private JsonObject _getCurrenciesAsJson() {
    return client.target(CURRENCY_URI).request().get().readEntity(JsonObject.class);
  }

  private JsonObject _getPricesAsJson() {
    return client.target(PRICES_URI).request().get().readEntity(JsonObject.class);
  }
}
