package eu.mosov.steamTradeHelper.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class InMemoryDataRepositoryTest {

	InMemoryDataRepository repo;

	@Before
	public void setUp() {
		repo = new InMemoryDataRepository();
	}

	@Test
	public void actuallyGetCurrencies() {
		repo.getCurrencies();
		assertTrue(repo.currencies != null);
		assertTrue(repo.updateCount == 1);
	}

	@Test
	public void dataUpdatesOnlyAfterCertainAmountOfTime() {
		repo.getCurrencies();
		repo.getCurrencies();
		assertTrue(repo.updateCount == 1);
	}
	
	@Test
	public void actuallyUpdatesOnDemand() {
		repo.getCurrencies();
		repo.dropRefreshTimer();
		repo.getCurrencies();
		assertTrue(repo.updateCount == 2);
	}
	
	@Test
	public void returnsAllCurrencies() {
		assertThat(repo.getCurrencies().size(), is(4));
	}
}