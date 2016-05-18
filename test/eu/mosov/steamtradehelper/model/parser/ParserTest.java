package eu.mosov.steamtradehelper.model.parser;

import org.junit.Before;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class ParserTest {
  JsonObject item;

  @Before
  public void setUp() throws Exception {
    item =
        Json.createObjectBuilder()
            .add("prices", addObject()
                               .add("6", addObject()
                                             .add("Tradable", addObject()
                                                                  .add("Craftable", JsonValue.TRUE)
                                                                  .add("Non-Craftable", JsonValue.TRUE)
                                             )
                                             .add("Non-Tradable", addObject()
                                                                      .add("Craftable", JsonValue.TRUE)
                                             ))
                               .add("11", JsonValue.NULL)
                               .add("0", Json.createArrayBuilder().add(JsonValue.TRUE))
            ).build();
  }

  /*=================================*/
  private static JsonObjectBuilder addObject() {
    return Json.createObjectBuilder();
  }
}