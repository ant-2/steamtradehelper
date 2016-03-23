package eu.mosov.steamTradeHelper.model;

import eu.mosov.steamTradeHelper.client.BackpackApiClient;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import java.util.Date;

@Repository
public class InMemoryDataRepository implements DataRepository {

	BackpackApiClient client = new BackpackApiClient();
	Date lastTimeUpdated;
	int updateInterval = 60 * 60 * 6 * 1000;	// updates data each 6 hours
	JsonObject currencies;
	JsonObject prices;
	int updateCount;

	public InMemoryDataRepository() {
		lastTimeUpdated = new Date();
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
	public DataRepository dropRefreshTimer() {
		lastTimeUpdated.setTime(lastTimeUpdated.getTime() - updateInterval * 2);
		return this;
	}

	/**
	 * Checks that were at least one day since last refresh
	 * @return true if it is
	 * */
	boolean isTimeToUpdate() {
		return System.currentTimeMillis() - lastTimeUpdated.getTime() > (updateInterval);
	}

	Date setLastTimeUpdated() {
		updateCount++;
		lastTimeUpdated.setTime(System.currentTimeMillis());
		return lastTimeUpdated;
	}
}
