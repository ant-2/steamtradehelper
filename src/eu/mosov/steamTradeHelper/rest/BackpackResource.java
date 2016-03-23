package eu.mosov.steamTradeHelper.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Date;

@Path("data") @Produces("text/json")
public class BackpackResource {
	Client client;
	Date lastTimeRefreshed;
	String CURRENCY = "http://backpack.tf/api/IGetCurrencies/v1/?key=56e4698ddea9e91a45b9de12";
	String PRICES = "http://backpack.tf/api/IGetPrices/v4/?key=56e4698ddea9e91a45b9de12";
	String todayCurrencies;
	String todayPrices;
	int currencyRefreshed = 0;
	int millisInDay = 60 * 60 * 24 * 1000;

	public BackpackResource() {
		client = ClientBuilder.newClient();
		lastTimeRefreshed = new Date();
	}

	@GET @Path("curr")
	public String getCurrency() {
		if (todayCurrencies == null || checkLastTimeRefreshed()) {
			todayCurrencies = client.target(CURRENCY).request().get().readEntity(String.class);
			setLastTimeRefreshed();
		}
		return todayCurrencies;
	}

	@GET @Path("prices")
	public String getPrices() {
		if (todayPrices == null || checkLastTimeRefreshed()) {
			System.out.println(++currencyRefreshed);
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
		return System.currentTimeMillis() - lastTimeRefreshed.getTime() >= (millisInDay);
	}

	Date setLastTimeRefreshed() {
		lastTimeRefreshed.setTime(System.currentTimeMillis());
		return lastTimeRefreshed;
	}
}
