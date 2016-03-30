package eu.mosov.steamTradeHelper.model;

import eu.mosov.steamTradeHelper.client.BackpackApiClient;
import eu.mosov.steamTradeHelper.entity.Item;
import eu.mosov.steamTradeHelper.entity.parser.Parser;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class InMemoryDataRepositoryTest {

	InMemoryDataRepository repo = new InMemoryDataRepository();

	@Before
	public void initMocks() {
		repo.client = mock(BackpackApiClient.class);
		//noinspection unchecked
		repo.parser = (Parser<Item>) mock(Parser.class);
	}

	@Test
	public void actuallyGetCurrencies() {
		when(repo.parser.parseCurrencies(null)).thenReturn(singletonList(new Item("metal")));

		List<Item> curr = repo.getCurrencies();
		assertTrue(repo.listCurr != null);
		assertThat(curr.size(), is (1));
		assertThat(curr.get(0).getName(), is("metal"));
	}

	@Test
	public void actuallyGetPrice() {
		when(repo.parser.parsePrices(null)).thenReturn(singletonList(new Item("metal")));

		List<Item> prices = repo.getPrices();
		assertTrue(repo.listPrices != null);
		assertThat(prices.size(), is(1));
		assertThat(prices.get(0).getName(), is("metal"));
	}

	@Test
	public void dataUpdatesOnlyAfterCertainAmountOfTime() {
		repo.getCurrencies();
		repo.getCurrencies();
		verify(repo.client, times(1)).getCurrenciesAsJson();
	}
	
	@Test
	public void actuallyUpdatesOnDemand() {
		repo.getCurrencies();
		repo.dropRefreshTimer();
		repo.getCurrencies();
		verify(repo.client, times(2)).getCurrenciesAsJson();
	}
}