package eu.mosov.steamtradehelper.model.entity.parser;

import eu.mosov.steamtradehelper.JsonMocks;
import eu.mosov.steamtradehelper.model.entity.Item;
import org.junit.Test;

import java.util.List;

import static eu.mosov.steamtradehelper.JsonMocks.Mocks.CURRENCIES;
import static eu.mosov.steamtradehelper.JsonMocks.Mocks.PRICES;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemParserTest {
	Parser<Item> parser = new ItemParser<>();

	@Test
	public void currenciesCorrectlyParsed() {
		List<Item> items = parser.parse(JsonMocks.getMock(CURRENCIES));
		assertThat(items.size(), is(1));
		assertThat(items.get(0).getName(), is("metal"));
	}

	@Test
	public void pricesCorrectlyParsed() {
		List<Item> items = parser.parse(JsonMocks.getMock(PRICES));
		assertThat(items.size(), is(1));
		assertThat(items.get(0).getName(), is("metal"));
	}
}