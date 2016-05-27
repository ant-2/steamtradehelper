package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.data.ItemRepo;
import eu.mosov.steamtradehelper.model.Item;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

/**
 * Provides direct access to data from backpack.tf site
 */
@Path("item")
@Produces({"application/json"})
public class ItemResource extends SpringAwareResource {
  @Autowired ItemRepo itemRepo;

  @GET
  public List<Item> getItem(@QueryParam("Name") String name) {
    return itemRepo.getItem(name);
  }

  @GET
  @Path("all")
  public Map<String, List<Item>> getAllItems(@QueryParam("Name") String name) {
    return itemRepo.getAllItems();
  }
}
