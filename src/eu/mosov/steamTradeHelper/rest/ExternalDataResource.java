package eu.mosov.steamTradeHelper.rest;

import eu.mosov.steamTradeHelper.client.BackpacktfApi;
import eu.mosov.steamTradeHelper.data.InMemoryDataRepo;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Provides direct access to data from backpack.tf site
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@Path("raw")
@Produces({"application/json", "application/javascript"})
public class ExternalDataResource extends SpringAwareResource {
  private static InMemoryDataRepo repo = Init.createRepo();

  @GET
  @Path("prices")
  public JsonObject getPricesBackpacktf() {
    return repo.getResource("prices");
  }

  private static class Init {
    private static InMemoryDataRepo createRepo() {
      BackpacktfApi api = new BackpacktfApi();
      InMemoryDataRepo repo = new InMemoryDataRepo();
      repo.putResource("prices", api.getPrices());
      return repo;
    }
  }
}
