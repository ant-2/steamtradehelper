package eu.mosov.steamTradeHelper.model;

import eu.mosov.steamTradeHelper.client.BackpackApiClient;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import java.util.Date;

@Repository
public class InMemoryDataRepository implements DataRepository {

	BackpackApiClient client;
	Date lastUpdateTime;
	int updateInterval = 60 * 60 * 6 * 1000;	// updates data each 6 hours
	JsonObject currencies;
	JsonObject prices;
	int updateCount;

	public InMemoryDataRepository() {
		client = new BackpackApiClient();
		lastUpdateTime = new Date();
	}

	@Override
	public JsonObject getCurrencies() {
		if (currencies == null || isTimeToUpdate()) {
			currencies = client.getCurrencies();
			setLastTimeUpdated();
		}
		return currencies;
	}

	@Override
	public JsonObject getPrices() {
		if (prices == null || isTimeToUpdate()) {
			prices = client.getPrices();
			setLastTimeUpdated();
		}
		return prices;
	}

	@Override
	public void dropRefreshTimer() {
		lastUpdateTime.setTime(lastUpdateTime.getTime() - (updateInterval * 2));
	}

	/**
	 * Checks that were at least one day since last refresh
	 * @return true if it is
	 * */
	boolean isTimeToUpdate() {
		return System.currentTimeMillis() - lastUpdateTime.getTime() > (updateInterval);
	}

	Date setLastTimeUpdated() {
		updateCount++;
		lastUpdateTime.setTime(System.currentTimeMillis());
		return lastUpdateTime;
	}
}
