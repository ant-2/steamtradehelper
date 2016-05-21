package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.data.InMemoryItemRepo;
import eu.mosov.steamtradehelper.model.Item;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import java.util.List;

/**
 * Provides direct access to data from backpack.tf site
 */
@Path("prices")
@Produces({"application/json", "application/javascript"})
public class ItemResource extends SpringAwareResource {
  @Autowired InMemoryItemRepo repo;

  @GET
  @Path("item")
  public Item getItemPrices(@QueryParam("Name") String name) {
    return repo.getItem(name);
  }

  @GET
  @Path("item")
  public List<Item> getItemsWithAttributes(@FormParam("quality") String quality) {
    return null;
  }
}
