package eu.mosov.steamTradeHelper.entity.parser;

import eu.mosov.steamTradeHelper.JsonMocks;
import eu.mosov.steamTradeHelper.entity.Item;
import org.junit.Test;

import java.util.List;

import static eu.mosov.steamTradeHelper.JsonMocks.Mocks.CURRENCIES;
import static eu.mosov.steamTradeHelper.JsonMocks.Mocks.PRICES;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParserImplTest {
	Parser<Item> parser = new ParserImpl<>();

	@Test
	public void currenciesCorrectlyParsed() {
		List<Item> items = parser.parseCurrencies(JsonMocks.getMock(CURRENCIES));
		assertThat(items.size(), is(1));
		assertThat(items.get(0).getName(), is("metal"));
	}

	@Test
	public void pricesCorrectlyParsed() {
		List<Item> items = parser.parsePrices(JsonMocks.getMock(PRICES));
		assertThat(items.size(), is(1));
		assertThat(items.get(0).getName(), is("metal"));
	}
}