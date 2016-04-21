package eu.mosov.steamtradehelper.model.entity.parser;

import eu.mosov.steamtradehelper.model.entity.Item;
import eu.mosov.steamtradehelper.model.entity.Quality;

import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemParser<E extends Item> implements Parser<Item> {

  @Override
  public List<Item> parse(JsonObject data) {
    List<Item> result = new ArrayList<>();

    JsonObject items = data.getJsonObject("response").getJsonObject("items");
    for (Map.Entry<String, JsonValue> e : items.entrySet()) {
      Item item = new Item(e.getKey());

      JsonObject rawItem = (JsonObject) e.getValue();
      JsonObject qualities = rawItem.getJsonObject("prices");

      for (Map.Entry<String, JsonValue> q : qualities.entrySet()) {
        item.qualities.add(
            Quality.Qualities.getQualityByGameId(
                Integer.valueOf(q.getKey())
            )
        );
      }

      result.add(item);
    }
    return result;
  }
}