package eu.mosov.steamTradeHelper.rest;

import eu.mosov.steamTradeHelper.model.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Resource to access backpack.tf developer API
 * */
@Path("data") @Produces("application/json")
public class RawDataResource extends SpringAwareResource {
	@Autowired
	DataRepository backpackRepository;

	@GET @Path("curr")
	public JsonObject getCurrency() {
		return backpackRepository.getCurrencies();
	}

	@GET @Path("prices")
	public JsonObject getPrices() {
		return backpackRepository.getPrices();
	}
}
