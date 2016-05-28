package eu.mosov.steamtradehelper.model;

import org.junit.Before;
import org.junit.Test;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import java.util.List;

import static eu.mosov.steamtradehelper.model.PricesExtractor.*;
import static javax.json.Json.createObjectBuilder;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParserBackpackTf_test {
  JsonObject item;

  @Before
  public void setUp() throws Exception {
    item =
        object().add("prices",
            object().add("6",
                object().add("Tradable",
                    object().add("Craftable",
                        object().add("0", object()
                                              .add("currency", "metal")
                                              .add("value", 4.66))))))
            .build();
  }

  private static JsonObjectBuilder object() {
    return createObjectBuilder();
  }

  @Test
  public void priceParsedWell() throws Exception {
    List<Price> prices = getPricesForQuality(item.getJsonObject("prices").getJsonObject("6"));

    assertThat(prices.size(), is(1));

    Price price = prices.get(0);
    assertTrue(price.currency.equals("metal"));
    assertTrue(price.value == 4.66);
  }
}