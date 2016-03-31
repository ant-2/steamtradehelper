package eu.mosov.steamTradeHelper.model;

import eu.mosov.steamTradeHelper.client.BackpackApiClient;
import eu.mosov.steamTradeHelper.entity.Item;

import javax.json.JsonObject;
import java.util.Date;
import java.util.List;

public class InMemoryDataRepository implements DataRepository {
	BackpackApiClient client;
	Date lastTimeUpdated;
	int updateInterval = 1000 * 60 * 60 * 6;	// updates data each 6 hours

	JsonObject currencies;
	List<Item> listCurr;
	JsonObject prices;
	List<Item> listPrices;

	public InMemoryDataRepository() {
		client = new BackpackApiClient();
		lastTimeUpdated = new Date();
	}

	@Override
	public List<Item> getCurrencies() {
		if (listCurr == null || isTimeToUpdate()) {
			listCurr = client.getCurrencies();
			setLastTimeUpdated();
		}
		return listCurr;
	}

	@Override
	public List<Item> getPrices() {
		if (listPrices == null || isTimeToUpdate()) {
			listPrices = client.getPrices();
			setLastTimeUpdated();
		}
		return listPrices;
	}

	public JsonObject getCurrenciesAsJson() {
		if (currencies == null || isTimeToUpdate()) {
			currencies = client.getCurrenciesAsJson();
			setLastTimeUpdated();
		}
		return currencies;
	}

	public JsonObject getPricesAsJson() {
		if (prices == null || isTimeToUpdate()) {
			prices = client.getPricesAsJson();
			setLastTimeUpdated();
		}
		return prices;
	}

	public DataRepository dropRefreshTimer() {
		lastTimeUpdated.setTime(lastTimeUpdated.getTime() - updateInterval * 2);
		return this;
	}

	public void updateBase() {
		dropRefreshTimer();
		getCurrenciesAsJson();
		dropRefreshTimer();
		getCurrencies();

		dropRefreshTimer();
		getPricesAsJson();
		dropRefreshTimer();
		getPrices();
	}

	/**
	 * Checks that were at least one day since last refresh
	 * @return true if it is
	 * */
	boolean isTimeToUpdate() {
		return System.currentTimeMillis() - lastTimeUpdated.getTime() > (updateInterval);
	}

	Date setLastTimeUpdated() {
		lastTimeUpdated.setTime(System.currentTimeMillis());
		return lastTimeUpdated;
	}
}
