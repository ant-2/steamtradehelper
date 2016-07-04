package eu.mosov.steamTradeHelper.rest;

import eu.mosov.steamTradeHelper.client.BackpacktfDevApi;
import org.springframework.beans.factory.annotation.Autowired;

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
public class BackpacktfApiResource extends SpringAwareResource {
  @Autowired private BackpacktfDevApi repo;
  //todo попробовать пофиксить ошибку с IncompleteJson, возникающую если закрыть вкладку браузера недожидаясь полной загрузки загружаемого ресурса

  @GET
  @Path("prices")
  public JsonObject getPricesBackpacktf() {
    return repo.getPrices();
  }
}
