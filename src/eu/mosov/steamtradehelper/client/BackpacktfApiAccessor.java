package eu.mosov.steamtradehelper.client;

import eu.mosov.steamtradehelper.ConfigReader;

import javax.json.JsonObject;

public class BackpacktfApiAccessor {
  private static final String PRICES = "http://backpack.tf/api/IGetPrices/v4";
  private static RestClient restClient;
  private ConfigReader configReader;

  public BackpacktfApiAccessor() {
    restClient = RestClient.getInstance();
    configReader = new ConfigReader("src/config.properties");
  }

  public JsonObject getPrices() {
    return restClient.getResourceAsType(PRICES+"/?key="+configReader.getBackpackApiKey(), JsonObject.class);
  }
}