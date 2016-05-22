package eu.mosov.steamtradehelper.rest;

public enum DataResourcesEnum {
  PRICES("http://backpack.tf/api/IGetPrices/v4"),
  CURRENCIES("http://backpack.tf/api/IGetCurrencies/v1");

  private static final String API_KEY = "56e4698ddea9e91a45b9de12";
  private String uri;

  DataResourcesEnum(String uri) {
    this.uri = uri+"?key="+API_KEY;
  }

  public String getURI() {
    return uri;
  }
}
