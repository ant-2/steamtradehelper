package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.client.BackpacktfApiAccessor;
import eu.mosov.steamtradehelper.data.InMemoryDataRepo;

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
  private static InMemoryDataRepo repo = Init.getRepoInstance();

  @GET
  @Path("prices")
  public JsonObject getPricesBackpacktf() {
    return repo.getResource("prices");
  }

  private static class Init {
    private static InMemoryDataRepo INSTANCE = null;

    private static InMemoryDataRepo createRepo() {
      BackpacktfApiAccessor api = new BackpacktfApiAccessor();
      InMemoryDataRepo repo = new InMemoryDataRepo();
      repo.putResource("prices", api.getPrices());
      return repo;
    }

    public static InMemoryDataRepo getRepoInstance() {
      if (INSTANCE == null) INSTANCE = createRepo();
      return INSTANCE;
    }
  }
}
