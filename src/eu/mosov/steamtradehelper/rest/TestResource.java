package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.model.Price;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("test")
@Produces({"application/json", "application/javascript"})
public class TestResource {

  @GET
  @Path("all")
  public List<Price> getPricesBackpacktf(@DefaultValue("false") @QueryParam("update") boolean update) {
    List<Price> list = new ArrayList<>();
    list.add(new Price("keys", 1)
                 .addProperty("Tradable", "1")
                 .addProperty("Craftable", "1"));

    list.add(new Price("metal", 19.33)
                 .addProperty("Tradable", "1")
                 .addProperty("Craftable", "1"));
    return Collections.unmodifiableList(list);
  }
}
