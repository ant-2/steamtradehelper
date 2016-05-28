package eu.mosov.steamtradehelper.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Price {
  public String currency;
  public double value;
  private Map<String, String> properties;

  public Price(String currency, double value) {
    this.currency = currency;
    this.value = value;
    this.properties = new HashMap<>();
  }

  public Price addProperty(String key, String value) {
    properties.put(key, value);
    return this;
  }

  public Map<String, String> getProperties() {
    return Collections.unmodifiableMap(properties);
  }
}
