package eu.mosov.steamtradehelper.rest;

public enum DataResourcesEnum {
  PRICES("http://backpack.tf/api/IGetPrices/v4", "56e4698ddea9e91a45b9de12"),
  CURRENCIES("http://backpack.tf/api/IGetCurrencies/v1", "56e4698ddea9e91a45b9de12"),
  MARKET("http://backpack.tf/api/IGetMarketPrices/v1", "56e4698ddea9e91a45b9de12"),
  STEAM_SCHEMA("http://api.steampowered.com/IEconItems_440/GetSchema/v0001/", "8B5B06A66CAC125B54FA99001F9465A1");

  private static final String Backpack_API_KEY = "56e4698ddea9e91a45b9de12";
  private static final String Steam_API_KEY = "8B5B06A66CAC125B54FA99001F9465A1";
  private String uri;

  DataResourcesEnum(String uri, String key) {
    this.uri = uri+"?key="+ key;
  }

  public String getURI() {
    return uri;
  }
}
