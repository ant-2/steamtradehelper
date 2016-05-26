package eu.mosov.steamtradehelper.model;

import org.springframework.stereotype.Service;

import javax.json.*;
import java.util.Collections;
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
                 Item item = new Item(e.getKey());
                 JsonValue itemJsonValue = e.getValue();
                 item.addProperty("quality", getQualities(itemJsonValue));
                 return item;
               })
               .collect(Collectors.toMap(Item::getName, item -> item));
  }

  /*-------private methods for parsing specific properties-------*/
  private static List<JsonObject> getQualities(JsonValue item) {
    if (!item.getValueType().equals(OBJECT)) {
      return Collections.emptyList();
    }

    JsonObject json = (JsonObject) item;
    return getKeysAsJsonObjects(json.getJsonObject("prices"));
  }

  //todo поченить парсинг карфт\трейд статусов
  private static void getTypes(JsonObject item) {
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
        .collect(Collectors.toList());
  }

  /*-----------------private utility methods---------------*/
  private static List<String> getKeys(JsonObject json) {
    return json
               .entrySet()
               .stream()
               .map(Map.Entry::getKey)
               .collect(Collectors.toList());
  }

  private static List<JsonObject> getKeysAsJsonObjects(JsonObject json) {
    return json
               .entrySet()
               .stream()
               .map(e -> {
                 return
                     Json.createObjectBuilder()
                         .add("key", e.getKey())
                         .add("value", e.getValue())
                         .build();
               })
               .collect(Collectors.toList());
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
