package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.client.BackpackApiClient;
import eu.mosov.steamtradehelper.model.entity.Item;
import eu.mosov.steamtradehelper.model.entity.parser.Parser;
import eu.mosov.steamtradehelper.model.entity.parser.ItemParser;

import javax.json.JsonObject;
import java.util.Date;
import java.util.List;

//todo написать реализацию методов
public class InMemoryDataRepository implements DataRepository {
  BackpackApiClient client;
  Parser<Item> parser;

  Date lastTimeUpdated;
  int updateInterval = 1000 * 60 * 60 * 6;    // updates data each 6 hours

  JsonObject currencies;
  List<Item> listCurr;
  JsonObject prices;
  List<Item> listPrices;

  public InMemoryDataRepository() {
    client = new BackpackApiClient();
    parser = new ItemParser<>();
    lastTimeUpdated = new Date();
  }

  @Override
  public Item saveOrUpdate(Item item) {
    return null;
  }

  @Override
  public List<Item> saveOrUpdateAll(List<Item> list) {
    return null;
  }

  @Override
  public List<Item> getAllItems() {
    if (listPrices == null || isTimeToUpdate()) {
      listPrices = parser.parse(client.getPrices());
      setLastTimeUpdated();
    }
    return listPrices;
  }

  @Override
  public Item find(String itemName) {
    return null;
  }

  public DataRepository dropRefreshTimer() {
    lastTimeUpdated.setTime(lastTimeUpdated.getTime() - updateInterval * 2);
    return this;
  }

  public void updateBase() {
    dropRefreshTimer();

    dropRefreshTimer();
  }

  /**
   * Checks that were at least one day since last refresh
   *
   * @return true if it is
   */
  boolean isTimeToUpdate() {
    return System.currentTimeMillis() - lastTimeUpdated.getTime() > (updateInterval);
  }

  Date setLastTimeUpdated() {
    lastTimeUpdated.setTime(System.currentTimeMillis());
    return lastTimeUpdated;
  }
}
