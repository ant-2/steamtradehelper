package eu.mosov.steamTradeHelper.model;

import eu.mosov.steamTradeHelper.client.BackpackApiClient;
import eu.mosov.steamTradeHelper.entity.Item;
import eu.mosov.steamTradeHelper.entity.parser.Parser;
import eu.mosov.steamTradeHelper.entity.parser.ParserImpl;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import java.util.Date;
import java.util.List;

@Repository
public class InMemoryDataRepository implements DataRepository {
	Parser<Item> parser;
	BackpackApiClient client;
	Date lastTimeUpdated;
	int updateInterval = 60 * 60 * 6 * 1000;	// updates data each 6 hours
	JsonObject currencies;
	JsonObject prices;
	int updateCount;

	public InMemoryDataRepository() {
		client = new BackpackApiClient();
		lastTimeUpdated = new Date();
		parser = new ParserImpl();
	}

	@Override
	public List<Item> getCurrencies() {
		if (currencies == null) {
			getCurrenciesAsJson();
		}
		return parser.parseCurrencies(this.currencies);
	}

	@Override
	public List<Item> getPrices() {
		if (prices == null) {
			getPricesAsJson();
		}
		return parser.parsePrices(this.prices);
	}

	@Override
	public JsonObject getCurrenciesAsJson() {
		if (currencies == null || isTimeToUpdate()) {
			currencies = client.getCurrencies();
			setLastTimeUpdated();
		}
		return currencies;
	}

	@Override
	public JsonObject getPricesAsJson() {
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
