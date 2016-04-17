package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.client.BackpackApiClient;
import eu.mosov.steamtradehelper.model.entity.Item;
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
	}

	@Test
	public void worksWell() {
		when(repo.getAllItems()).thenReturn(singletonList(new Item("metal")));

		List<Item> prices = repo.getAllItems();
		assertTrue(repo.listPrices != null);
		assertThat(prices.size(), is(1));
		assertThat(prices.get(0).getName(), is("metal"));
	}

	@Test
	public void dataUpdatesOnlyAfterCertainAmountOfTime() {
		repo.getAllItems();
		repo.getAllItems();
		verify(repo.client, times(1)).getCurrencies();
	}
	
	@Test
	public void actuallyUpdatesOnDemand() {
		repo.getAllItems();
		repo.dropRefreshTimer();
		repo.getAllItems();
		verify(repo.client, times(2)).getCurrencies();
	}
}