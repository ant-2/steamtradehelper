package eu.mosov.steamtradehelper.data;

import eu.mosov.steamtradehelper.model.Item;

import java.util.List;
import java.util.Map;

interface ItemRepository {

  Item getItem(String name);

  List<Item> getAllItems();

  List<Item> getItemsWithAttributes(Map<String, String> attributes);
}
