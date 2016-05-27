package eu.mosov.steamtradehelper.model;

public class Price {
  public String priceIndex;
  public String currency;
  public String value;

  public Price(String priceIndex) {
    this.priceIndex = priceIndex;
  }

  public Price(String priceIndex, String currency, String value) {
    this.priceIndex = priceIndex;
    this.currency = currency;
    this.value = value;
  }
}
