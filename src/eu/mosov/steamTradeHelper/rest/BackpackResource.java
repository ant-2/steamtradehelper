package eu.mosov.steamTradeHelper.rest;

import eu.mosov.steamTradeHelper.model.PricesRepository;
import eu.mosov.steamTradeHelper.model.WebPricesRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("data") @Produces("text/json")
public class BackpackResource {
	PricesRepository repository = new WebPricesRepository();

	@GET @Path("curr")
	public String getCurrency() {
		return repository.getCurrencies();
	}

	@GET @Path("prices")
	public String getPrices() {
		return repository.getPrices();
	}
}
