package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.client.BackpackApiClient;
import eu.mosov.steamtradehelper.model.entity.Item;
import eu.mosov.steamtradehelper.model.entity.parser.ItemParser;
import eu.mosov.steamtradehelper.model.entity.parser.Parser;

import java.util.Date;
import java.util.List;

public class InMemoryDataRepository implements DataRepository {
  BackpackApiClient client;
  Parser<Item> parser;

  Date lastTimeUpdated;
  int updateInterval = 1000 * 60 * 60;    // updates data each hour
  
  List<Item> listPrices;

  public InMemoryDataRepository() {
    client = new BackpackApiClient();
    parser = new ItemParser<>();
    lastTimeUpdated = new Date();
  }

  @Override
  public List<Item> loadAll() {
    if (listPrices == null || isTimeToUpdate()) {
      listPrices = parser.parse(client.getPrices());
      setLastTimeUpdated();
    }
    return listPrices;
  }

  public DataRepository dropRefreshTimer() {
    lastTimeUpdated.setTime(lastTimeUpdated.getTime() - updateInterval * 2);
    return this;
  }

  boolean isTimeToUpdate() {
    return System.currentTimeMillis() - lastTimeUpdated.getTime() > (updateInterval);
  }

  Date setLastTimeUpdated() {
    lastTimeUpdated.setTime(System.currentTimeMillis());
    return lastTimeUpdated;
  }
}
