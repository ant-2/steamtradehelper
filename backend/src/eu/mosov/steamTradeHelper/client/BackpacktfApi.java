package eu.mosov.steamTradeHelper.client;

import eu.mosov.steamTradeHelper.ConfigReader;

import javax.json.JsonObject;

public class BackpacktfApi {
  private static final String PRICES = "http://backpack.tf/api/IGetPrices/v4";
  private static RestClient restClient;
  private ConfigReader configReader;

  public BackpacktfApi() {
    restClient = RestClient.getInstance();
    configReader = new ConfigReader("src/config.properties");
  }

  public JsonObject getPrices() {
    return restClient.getResourceAsType(PRICES+"/?key="+configReader.getBackpackApiKey(), JsonObject.class);
  }
}