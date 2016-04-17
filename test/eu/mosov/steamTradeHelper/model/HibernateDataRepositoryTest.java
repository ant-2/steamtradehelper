package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.model.entity.Item;
import eu.mosov.steamtradehelper.model.entity.Quality;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateOperations;

import java.util.HashSet;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HibernateDataRepositoryTest {
  HibernateDataRepository repo = new HibernateDataRepository();

  @Before
  public void initMocks() {
    repo.hibernate = mock(HibernateOperations.class);
  }

  @Test
  public void allItemsLoaded() {
    Item item = new Item(e -> {
      e.name = "metal";
      e.qualities = new HashSet<>();
      e.qualities.add(new Quality("Unique"));
    });
    when(repo.hibernate.loadAll(Item.class)).thenReturn(asList(item));

    List<Item> items = repo.getAllItems();
    assertThat(items.size(), is(1));
    assertThat(items.get(0).name, is("metal"));
    assertTrue(items.get(0).qualities.contains(new Quality("Unique")));
  }
}