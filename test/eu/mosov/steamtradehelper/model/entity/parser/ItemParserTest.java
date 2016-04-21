package eu.mosov.steamtradehelper.model.entity.parser;

import eu.mosov.steamtradehelper.model.entity.Item;
import eu.mosov.steamtradehelper.model.entity.Quality;
import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemParserTest {
  Parser<Item> parser = new ItemParser<>();
  JsonBuilderFactory f = Json.createBuilderFactory(null);
  JsonObject jsonToParse;

  @Before
  public void setUp() throws Exception {
    jsonToParse = f.createObjectBuilder().add("response",
        f.createObjectBuilder().add("items",
            f.createObjectBuilder().add("itemName",
                f.createObjectBuilder().add("prices",
                    f.createObjectBuilder()
                        .add("6", "null").add("11", "null")
                )
            )
        )
    ).build();
  }

  @Test
  public void itemCorrectlyParsed() throws Exception {
    List<Item> list = parser.parse(jsonToParse);

    assertThat(list.size(), is(1));

    Item item = list.get(0);
    assertThat(item.getName(), is("itemName"));
    assertThat(item.getQualities().size(), is(2));
    assertThat(item.getQualities().contains(new Quality("Unique", 6)), is(true));
    assertThat(item.getQualities().contains(new Quality("Strange", 11)), is(true));
  }
}