package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.data.InMemoryItemRepo;
import eu.mosov.steamtradehelper.model.Item;
import org.springframework.beans.factory.annotation.Autowired;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Provides direct access to data from backpack.tf site
 */
@Path("parsed")
@Produces({"application/json", "application/javascript"})
public class ItemResource extends SpringAwareResource {
  @Autowired InMemoryItemRepo<String, Item, JsonObject> repo;

  @GET
  @Path("resource")
  public Item getItemPrices(@QueryParam("Name") String name) {
    return repo.get(name);
  }
}
