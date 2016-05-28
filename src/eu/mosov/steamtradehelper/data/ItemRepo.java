package eu.mosov.steamtradehelper.data;

import eu.mosov.steamtradehelper.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eu.mosov.steamtradehelper.rest.DataResourcesEnum.PRICES;

@Repository
@Scope("singleton")
public class ItemRepo {
  private static final Map<String, List<Item>> mem = new HashMap<>();
  @Autowired private OldPricesParser dataConverter;
  @Autowired private RawDataRepo repo;
  private volatile boolean isInitialized;

  private void checkInitStatus() {
    if (!isInitialized) init();
  }

  private synchronized void init() {
    JsonObject prices = repo.getResource("prices", PRICES.getURI(), true);
    mem.putAll(dataConverter.parse(prices.getJsonObject("response").getJsonObject("items")));
    isInitialized = true;
  }

  public boolean isInitialized() {
    return isInitialized;
  }

  public List<Item> getItem(String name) {
    checkInitStatus();
    return Collections.unmodifiableList(mem.get(name));
  }

  public Map<String, List<Item>> getAllItems() {
    checkInitStatus();
    return Collections.unmodifiableMap(mem);
  }
}