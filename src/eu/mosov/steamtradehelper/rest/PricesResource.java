package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.model.Price;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collections;
import java.util.List;

/**
 * Provides direct access to data from backpack.tf site
 */
@Path("btf")
@Produces({"application/json"})
public class PricesResource extends SpringAwareResource {

  @GET
  @Path("all")
  public List<Price> getAllPrices() {
    return Collections.emptyList();
  }
}
