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
public class ExternalDataResource extends SpringAwareResource {
	InMemoryDataRepository repo = new InMemoryDataRepository();

	@GET
	@Path("parsed/curr")
	public JsonObject getCurrencies() {
		return repo.getCurrenciesAsJson();
	}

	@GET
	@Path("parsed/prices")
	public JsonObject getPrices() {
		return repo.getPricesAsJson();
	}

	@GET
	@Path("full/prices")
	public JsonObject getFullPricesResponse() {
		return repo.client.getPricesAsJson();
	}

	@GET
	@Path("full/curr")
	public JsonObject getFullCurrenciesResponse() {
		return repo.client.getCurrenciesAsJson();
	}
}
