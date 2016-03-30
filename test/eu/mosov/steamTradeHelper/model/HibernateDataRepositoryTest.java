package eu.mosov.steamTradeHelper.model;

import eu.mosov.steamTradeHelper.entity.Item;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateOperations;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HibernateDataRepositoryTest {
	HibernateDataRepository repo = new HibernateDataRepository();

	@Before
	public void initMocks() {
		repo.hibernate =  mock(HibernateOperations.class);
	}
	
	@Test
	public void allItemAreLoaded() {
		when(repo.hibernate.loadAll(Item.class)).thenReturn(asList(new Item("metal")));

		List<Item> curr= repo.getCurrencies();
		assertThat(curr.size(), is(1));
		assertThat(curr.get(0).getName(), is("metal"));
	}
}