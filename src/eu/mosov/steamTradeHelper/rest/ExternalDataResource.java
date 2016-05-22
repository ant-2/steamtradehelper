package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.data.RawDataRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.json.JsonObject;
import javax.ws.rs.*;

import static eu.mosov.steamtradehelper.rest.DataResourcesEnum.*;

/**
 * Provides direct access to data from backpack.tf site
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@Path("raw")
@Produces({"application/json", "application/javascript"})
public class ExternalDataResource extends SpringAwareResource {
  @Autowired RawDataRepo repo;

  @GET
  @Path("prices")
  public JsonObject getPricesBackpacktf(@DefaultValue("false") @QueryParam("update") boolean update) {
    return repo.getResource("prices", PRICES.getURI(), update);
  }

  @GET
  @Path("curr")
  public JsonObject getCurrenciesBackpacktf(@DefaultValue("false") @QueryParam("update") boolean update) {
    return repo.getResource("curr", CURRENCIES.getURI(), update);
  }
}
