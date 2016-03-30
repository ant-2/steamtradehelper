package eu.mosov.steamTradeHelper.rest;

import eu.mosov.steamTradeHelper.model.InMemoryDataRepository;

import javax.json.JsonObject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("raw") @Produces({"application/json", "application/javascript"})
public class BackpackTfResource extends SpringAwareResource {
	InMemoryDataRepository repo = new InMemoryDataRepository();

	@Path("curr")
	public JsonObject getCurrencies() {
		return repo.getCurrenciesAsJson();
	}

	@Path("prices")
	public JsonObject getPrices() {
		return repo.getPricesAsJson();
	}
}
