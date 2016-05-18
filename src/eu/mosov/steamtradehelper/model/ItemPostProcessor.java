package eu.mosov.steamtradehelper.model;

import org.springframework.stereotype.Service;

import javax.json.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ItemPostProcessor implements DataPostProcessor<String, Item, JsonObject> {

  @Override
  public Map<String, Item> parse(JsonObject json) {
    return null;
  }

  /*-----------------private utility methods---------------*/
  private static void getTypes(JsonObject item) {
    List<JsonObject> list = new ArrayList<>();
    item.entrySet()
        .stream()
        .filter(e -> {
          JsonValue.ValueType type = e.getValue().getValueType();
          return type.equals(JsonValue.ValueType.OBJECT);
        })
        .map(e -> {
          return Json.createObjectBuilder()
                     .add(e.getKey(), e.getValue())
                     .build();
        })
        .forEach(list::add);
  }

  private static JsonArray getKeysAsJsonArray(JsonObject json) {
    JsonArrayBuilder builder = Json.createArrayBuilder();

    json
        .entrySet()
        .stream()
        .forEach(e -> builder.add(e.getKey()));

    return builder.build();
  }
}
