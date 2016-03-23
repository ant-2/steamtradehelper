package eu.mosov.steamTradeHelper.model;

import javax.json.JsonObject;

/**
 * Repository to encapsulate data that retrieved via backpack.tf API
 * */
public interface DataRepository {
	JsonObject getCurrencies();

	JsonObject getPrices();

	void dropRefreshTimer();
}
