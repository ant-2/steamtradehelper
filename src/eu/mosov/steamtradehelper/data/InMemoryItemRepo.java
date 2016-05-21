package eu.mosov.steamtradehelper.data;

import eu.mosov.steamtradehelper.model.Item;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryItemRepo implements ItemRepository {
  private static final Map<String, Item> mem = new HashMap<>();

  @Override
  public Item getItem(String name) {
    return mem.get(name);
  }

  @Override
  public List<Item> getAllItems() {
    return mem
               .entrySet()
               .stream()
               .map(Map.Entry::getValue)
               .collect(Collectors.toList());
  }

  /**
   * Ключи мапы - имя группы аттрибутов (quality, craftable, etc)
   * Велью - значения по которым ищем.
   * */
  @Override
  public List<Item> getItemsWithAttribute(String attributeGroupName, List<String> attributes) {
    List<Item> result = new ArrayList<>();

    processElements(
        mem.entrySet(),
        e -> {
          Map<String, List<JsonObject>> itemAttr = e.getValue().getAttributes();

          if (itemAttr.containsKey(attributeGroupName)) return false; // item hasn't attributes of such group
          List<JsonObject> jsonObjects = itemAttr.get(attributeGroupName);
          for (String a : attributes) {

          }

          return true;
        },
        Map.Entry::getValue,
        result::add
        );

    return result;
  }
}