package eu.mosov.steamTradeHelper.entity.parser;

import eu.mosov.steamTradeHelper.entity.Item;
import eu.mosov.steamTradeHelper.model.DataRepository;
import eu.mosov.steamTradeHelper.model.InMemoryDataRepository;
import org.junit.Test;

import javax.json.JsonObject;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ParserImplIntegrationTest {

	Parser<Item> parser = new ParserImpl();
	DataRepository repo = new InMemoryDataRepository();

	@Test
	public void allCurrenciesParsed() {
		JsonObject curr = repo.getCurrenciesAsJson();
		List<Item> list = parser.parseCurrencies(curr);
		assertThat(list.size(), is(4));
	}

	@Test
	public void parseAllPrices() {
		JsonObject prices = repo.getPricesAsJson();
		List<Item> list = parser.parsePrices(prices);
		assertTrue(list.size() > 10);
	}
}