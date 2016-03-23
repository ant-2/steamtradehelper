package eu.mosov.steamTradeHelper.rest;

import eu.mosov.steamTradeHelper.entity.Item;
import eu.mosov.steamTradeHelper.model.DataRepository;
import org.glassfish.jersey.server.JSONP;
import org.springframework.beans.factory.annotation.Autowired;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Resource to access backpack.tf developer API
 * */
@Path("data")
@Produces({"application/json", "application/javascript"})
public class DataResource extends SpringAwareResource {
	@Autowired DataRepository repo;

	@GET @JSONP
	@Path("curr")
	public List<Item> getCurrencies() {
		return repo.getCurrencies();
	}

	@GET @JSONP
	@Path("prices")
	public List<Item> getPrices() {
		return repo.getPrices();
	}

	@GET
	@Path("raw/curr")
	public JsonObject getCurrenciesAsJson() {
		return repo.getCurrenciesAsJson();
	}

	@GET
	@Path("raw/prices")
	public JsonObject getPricesAsJson() {
		return repo.getPricesAsJson();
	}
}