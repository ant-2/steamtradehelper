package eu.mosov.steamtradehelper.rest;

import eu.mosov.steamtradehelper.model.PropertiesImpl;
import eu.mosov.steamtradehelper.model.TestEntity;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("test")
@Produces({"application/json", "application/javascript"})
public class TestResource {

  @GET
  @Path("all")
  public List<TestEntity> getPricesBackpacktf(@DefaultValue("false") @QueryParam("update") boolean update) {
    List<TestEntity> mem = new ArrayList<>();
    PropertiesImpl properties = new PropertiesImpl(e -> {
      e.quality = 6;
      e.tradable = "Tradable";
      e.craftable = "Craftable";
    });

    mem.add(new TestEntity("item 1").setProperties(properties));
    mem.add(new TestEntity("item 2").setProperties(properties));
    mem.add(new TestEntity("item 3").setProperties(properties));

    return Collections.unmodifiableList(mem);
  }
}
