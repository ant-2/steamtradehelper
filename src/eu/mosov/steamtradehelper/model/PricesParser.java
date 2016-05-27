package eu.mosov.steamtradehelper.model;

import org.springframework.stereotype.Service;

import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PricesParser {

  public Map<String, List<Item>> parse(JsonObject json) {
    System.out.println("Start parsing items");
    long start = System.currentTimeMillis();
    Map<String, List<Item>> map = new HashMap<>();

    for (Map.Entry<String, JsonValue> item : json.entrySet()) {
      String itemName = item.getKey();
      JsonObject itemProperties = (JsonObject) item.getValue();

      map.put(
          item.getKey(),
          parseSpecificItems(
              itemName,
              itemProperties
          )
      );
    }

    long end = System.currentTimeMillis();
    System.out.println("Parsing are done in "+ (end - start)+" milliseconds");

    return map;
  }

  static List<Item> parseSpecificItems(String name, JsonObject itemProperties) {
    ArrayList<Item> list = new ArrayList<>();

    for (Map.Entry<String, JsonValue> quality : itemProperties.getJsonObject("prices").entrySet()) {
      JsonObject q = (JsonObject) quality.getValue();

      for (Map.Entry<String, JsonValue> tradable : q.entrySet()) {
        JsonObject tr = (JsonObject) tradable.getValue();

        for (Map.Entry<String, JsonValue> craftable : tr.entrySet()) {
          JsonObject cr = (JsonObject) craftable.getValue();

          Item item = new Item(name);
          item.quality = quality.getKey();
          item.tradable = tradable.getKey();
          item.craftable = craftable.getKey();

          for (Map.Entry<String, JsonValue> priceIndex : cr.entrySet()) {
            JsonObject value = (JsonObject) priceIndex.getValue();

            Price price = new Price(priceIndex.getKey());
            price.currency = value.getString("currency");
            price.value = value.getJsonNumber("value").toString();

            item.priceIndexes.add(price);
          }

          list.add(item);
        }
      }

    }

    return list;
  }
}
