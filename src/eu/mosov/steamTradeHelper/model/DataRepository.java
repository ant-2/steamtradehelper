package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.model.entity.Item;

import java.util.List;

/**
 * Repository to encapsulate data that retrieved via backpack.tf API
 */
public interface DataRepository {

  Item saveOrUpdate(Item item);

  List<Item> saveOrUpdateAll(List<Item> list);

  List<Item> getAllItems();

  Item find(String itemName);
}
