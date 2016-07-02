package eu.mosov.steamTradeHelper.client;

import javax.json.JsonObject;

public class BackpacktfApi {
  private final String PRICES = "http://backpack.tf/api/IGetPrices/v4";
  private RestClient restClient;
  private String apiKey;

  // todo инжектит RestClient
  public BackpacktfApi(String apiKey) {
    restClient = RestClient.getInstance();
    this.apiKey = apiKey;
  }

  public JsonObject getPrices() {
    return restClient.getResourceAsType(PRICES+"/?key="+ apiKey, JsonObject.class);
  }
}