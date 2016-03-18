package eu.mosov.steamTradeHelper.rest;

import eu.mosov.steamTradeHelper.model.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("data") @Produces("text/json")
public class BackpackResource extends SpringAwareResource {
	@Autowired PricesRepository pricesRepository;

	@GET @Path("curr")
	public String getCurrency() {
		return pricesRepository.getCurrencies();
	}

	@GET @Path("prices")
	public String getPrices() {
		return pricesRepository.getPrices();
	}
}
