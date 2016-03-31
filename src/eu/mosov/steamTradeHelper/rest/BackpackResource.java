package eu.mosov.steamTradeHelper.rest;

import eu.mosov.steamTradeHelper.model.InMemoryDataRepository;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Provides direct access to data from backpack.tf site
 * */
@Path("raw") @Produces({"application/json", "application/javascript"})
public class BackpackResource extends SpringAwareResource {
	InMemoryDataRepository repo = new InMemoryDataRepository();

	@GET
	@Path("curr")
	public JsonObject getCurrencies() {
		return repo.getCurrenciesAsJson();
	}

	@GET
	@Path("prices")
	public JsonObject getPrices() {
		return repo.getPricesAsJson();
	}
}
