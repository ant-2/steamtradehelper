package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.model.entity.Item;
import eu.mosov.steamtradehelper.model.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Resource to access backpack.tf developer API
 */
@Path("data")
@Produces({"application/json", "application/javascript"})
public class DataResource extends SpringAwareResource {
  @Autowired
  DataRepository repo;

  @GET
  @Path("items")
  public List<Item> getItems() {
    return repo.getAllItems();
  }
}