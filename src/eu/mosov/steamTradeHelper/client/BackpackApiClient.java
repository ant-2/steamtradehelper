package eu.mosov.steamTradeHelper.client;

import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Makes requests to backpack.tf API
 * */
@Repository
public class BackpackApiClient {
	String CURRENCY = "http://backpack.tf/api/IGetCurrencies/v1/?key=56e4698ddea9e91a45b9de12";
	String PRICES = "http://backpack.tf/api/IGetPrices/v4/?key=56e4698ddea9e91a45b9de12";

	Client client;

	public BackpackApiClient() {
		client = ClientBuilder.newClient();
	}

	public JsonObject getCurrencies() {
		return client.target(CURRENCY).request().get().readEntity(JsonObject.class);
	}

	public JsonObject getPrices() {
		return client.target(PRICES).request().get().readEntity(JsonObject.class);
	}
}
