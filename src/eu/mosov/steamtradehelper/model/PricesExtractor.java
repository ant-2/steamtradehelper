package eu.mosov.steamtradehelper.model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PricesExtractor {
  private JsonObject prices;

  public PricesExtractor(JsonObject prices) {
    this.prices = prices;
  }

  public List<Price> getPricesForQuality(JsonObject quality) {
    List<Price> result = new ArrayList<>();

    List<String> tradeStatuses = getKeys(quality);
    for (String tradeSt : tradeStatuses) {
      List<String> craftStatuses = getKeys(quality.getJsonObject(tradeSt));

      for (String craftSt : craftStatuses) {
        List<String> priceIndexes = getKeys(quality.getJsonObject(tradeSt).getJsonObject(craftSt));

        for (String priceInd : priceIndexes) {
          JsonObject priceIndexObject = quality.getJsonObject(tradeSt).getJsonObject(craftSt).getJsonObject(priceInd);
          Price price = parsePriceIndex(priceIndexObject);
          price.addProperty("priceIndex", priceInd);
          price.addProperty("tradable", tradeSt);
          price.addProperty("craftable", craftSt);

          result.add(price);
        }
      }
    }

    return result;
  }

  public List<String> getKeys(JsonObject jsonObject) {
    return
        jsonObject
            .entrySet()
            .stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
  }

  /* Private utility methods */
  private JsonObject castToJsonObject(JsonValue value) {
    if (!value.getValueType().equals(JsonValue.ValueType.OBJECT)) {
      return Json.createObjectBuilder().build();
    }
    return (JsonObject) value;
  }

  /**
   * Shorthand for remove code duplication.
   * Parse price index objects. Like this one
   * "0":{
   *    "currency":"metal",
   *    "value":4.66,
   *    "last_update":1462143588,
   *    "difference":-0.34
   * }
   */
  private static Price parsePriceIndex(JsonObject price) {
    return new Price(
                        price.getString("currency"),
                        price.getJsonNumber("value").doubleValue()
    );
  }
}
