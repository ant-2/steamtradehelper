package eu.mosov.steamTradeHelper.rest;

import eu.mosov.steamTradeHelper.entity.Item;
import eu.mosov.steamTradeHelper.model.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
	@SuppressWarnings("SpringJavaAutowiredMembersInspection")
	@Autowired DataRepository repo;

	@GET
	@Path("curr")
	public List<Item> getCurrencies() {
		return repo.getCurrencies();
	}

	@GET
	@Path("prices")
	public List<Item> getPrices() {
		return repo.getPrices();
	}
}