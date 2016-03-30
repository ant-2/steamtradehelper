package eu.mosov.steamTradeHelper.client;

import eu.mosov.steamTradeHelper.entity.Item;
import eu.mosov.steamTradeHelper.entity.parser.Parser;
import eu.mosov.steamTradeHelper.entity.parser.ParserImpl;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.List;

/**
 * Makes requests to backpack.tf API
 * */
@Repository
public class BackpackApiClient {
	String CURRENCY = "http://backpack.tf/api/IGetCurrencies/v1/?key=56e4698ddea9e91a45b9de12";
	String PRICES = "http://backpack.tf/api/IGetPrices/v4/?key=56e4698ddea9e91a45b9de12";

	Client client;
	Parser<Item> parser;

	public BackpackApiClient() {
		client = ClientBuilder.newClient();
		parser = new ParserImpl<>();
	}

	public JsonObject getCurrenciesAsJson() {
		return _getCurrenciesAsJson();
	}

	public JsonObject getPricesAsJson() {
		return _getPricesAsJson();
	}

	public List<Item> getCurrencies() {
		return parser.parseCurrencies(_getCurrenciesAsJson());
	}

	public List<Item> getPrices() {
		return parser.parsePrices(_getPricesAsJson());
	}

	/*private methods for remove code duplication*/
	private JsonObject _getCurrenciesAsJson() {
		return client.target(CURRENCY).request().get().readEntity(JsonObject.class);
	}

	private JsonObject _getPricesAsJson() {
		return client.target(PRICES).request().get().readEntity(JsonObject.class);
	}
}
