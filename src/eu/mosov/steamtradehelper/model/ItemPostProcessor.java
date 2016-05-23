package eu.mosov.steamtradehelper.model;

import org.springframework.stereotype.Service;

import javax.json.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javax.json.JsonValue.ValueType;
import static javax.json.JsonValue.ValueType.OBJECT;

@Service
public class ItemPostProcessor {

  public Map<String, Item> parse(JsonObject json) {
    return json
        .entrySet()
        .stream()
        .map(e -> {
          Item item  =new Item(e.getKey());
          JsonValue itemJsonValue = e.getValue();
          item.addProperty("quality", getQualities(itemJsonValue));
          return item;
        })
        .collect(Collectors.toMap(Item::getName, item -> item));
  }

  /*-------private methods for parsing specific properties-------*/
  private static List<String> getQualities(JsonValue item) {
    if (!item.getValueType().equals(OBJECT)) {
      throw new RuntimeException("");
    }
    JsonObject itemAsObject = (JsonObject) item;

    return getKeys(itemAsObject.getJsonObject("prices"));
  }

  /*-----------------private utility methods---------------*/
  private static List<String> getKeys(JsonObject json) {
    return json
        .entrySet()
        .stream()
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  private static void getTypes(JsonObject item) {
    List<JsonObject> list = new ArrayList<>();
    item.entrySet()
        .stream()
        .filter(e -> {
          ValueType type = e.getValue().getValueType();
          return type.equals(OBJECT);
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
