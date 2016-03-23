package eu.mosov.steamTradeHelper.model;

import eu.mosov.steamTradeHelper.entity.Item;

import javax.json.JsonObject;
import java.util.List;

/**
 * Repository to encapsulate data that retrieved via backpack.tf API
 * */
public interface DataRepository {

	List<Item> getCurrencies();

	List<Item> getPrices();

	JsonObject getCurrenciesAsJson();

	JsonObject getPricesAsJson();

	DataRepository dropRefreshTimer();
}
