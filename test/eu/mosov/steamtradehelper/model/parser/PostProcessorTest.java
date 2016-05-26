package eu.mosov.steamtradehelper.model.parser;

import eu.mosov.steamtradehelper.model.Item;
import eu.mosov.steamtradehelper.model.ItemPostProcessor;
import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PostProcessorTest {
  JsonObject item;
  ItemPostProcessor processor = new ItemPostProcessor();

  @Before
  public void setUp() throws Exception {
    item = Json.createObjectBuilder()
           .add("A Brush with Death", jsonObject()
                                      .add("prices", jsonObject()
                                                     .add("6", jsonObject()
                                                               .add("Tradable", jsonObject()
                                                                                .add("Craftable", JsonValue.TRUE)
                                                                                .add("Non-Craftable", JsonValue.TRUE))
                                                               .add("Non-Tradable", jsonObject()
                                                                                    .add("Craftable", JsonValue.TRUE))
                                                     )
                                                     .add("11", JsonValue.NULL)
                                                     .add("0", Json.createArrayBuilder().add(JsonValue.TRUE))
                                      )
           ).build();
  }

  @Test
  public void itemsParsedWell() throws Exception {
    Map<String, Item> items = processor.parse(item);
    assertThat(items.size(), is(1));

    Item item = items.get("A Brush with Death");
    assertThat(item.hasGroup("quality"), is(true));
    List<JsonObject> qualities = item.getGroup("quality");
    assertThat(qualities.size(), is(3));
  }

  /*=================================*/
  private static JsonObjectBuilder jsonObject() {
    return Json.createObjectBuilder();
  }
}