package eu.mosov.steamtradehelper.data;

import eu.mosov.steamtradehelper.model.Item;
import eu.mosov.steamtradehelper.model.ItemPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.json.JsonObject;
import java.util.*;
import java.util.stream.Collectors;

import static eu.mosov.steamtradehelper.rest.DataResourcesEnum.PRICES;

@Repository
@Scope("singleton")
public class ItemRepo {
  private static final Map<String, Item> mem = new HashMap<>();
  @Autowired private ItemPostProcessor dataConverter;
  @Autowired private RawDataRepo repo;
  private boolean isInitialized;

  @PostConstruct
  private void init() {
      JsonObject prices = repo.getResource("prices", PRICES.getURI(), true);
      mem.putAll(dataConverter.parse(prices.getJsonObject("response").getJsonObject("items")));
      isInitialized = true;
  }

  public boolean isInitialized() {return isInitialized;}

  public Item getItem(String name) {
    return mem.get(name);
  }

  public List<Item> getAllItems() {
    List<Item> result = new ArrayList<>(1800);
    try {
      result = mem
                   .entrySet()
                   .stream()
                   .map(Map.Entry::getValue)
                   .collect(Collectors.toList());

      result.sort((a, b) -> a.getName().compareTo(b.getName()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}