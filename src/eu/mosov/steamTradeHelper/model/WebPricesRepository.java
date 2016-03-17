package eu.mosov.steamTradeHelper.model;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Date;

public class WebPricesRepository implements PricesRepository {
	String CURRENCY = "http://backpack.tf/api/IGetCurrencies/v1/?key=56e4698ddea9e91a45b9de12";
	String PRICES = "http://backpack.tf/api/IGetPrices/v4/?key=56e4698ddea9e91a45b9de12";

	Client client;
	Date lastTimeRefreshed;
	String todayCurrencies;
	String todayPrices;
	int millisInDay = 60 * 60 * 24 * 1000;

	public WebPricesRepository() {
		client = ClientBuilder.newClient();
		lastTimeRefreshed = new Date();
	}

	@Override
	public String getCurrencies() {
		if (todayCurrencies == null || checkLastTimeRefreshed()) {
			todayCurrencies = client.target(CURRENCY).request().get().readEntity(String.class);
			setLastTimeRefreshed();
		}
		return todayCurrencies;
	}

	@Override
	public String getPrices() {
		if (todayPrices == null || checkLastTimeRefreshed()) {
			todayPrices = client.target(PRICES).request().get().readEntity(String.class);
			setLastTimeRefreshed();
		}
		return todayPrices;
	}

	/**
	 * Checks that were at least one day since last refresh
	 * @return true if it is
	 * */
	boolean checkLastTimeRefreshed() {
		return System.currentTimeMillis() - lastTimeRefreshed.getTime() > (millisInDay);
	}

	Date setLastTimeRefreshed() {
		lastTimeRefreshed.setTime(System.currentTimeMillis());
		return lastTimeRefreshed;
	}


}
