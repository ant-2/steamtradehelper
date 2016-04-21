package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.client.BackpackApiClient;
import eu.mosov.steamtradehelper.model.entity.Item;
import eu.mosov.steamtradehelper.model.entity.Quality;
import eu.mosov.steamtradehelper.model.entity.parser.Parser;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.*;

@SuppressWarnings("ALL")
public class InMemoryDataRepositoryTest {

  InMemoryDataRepository repo = new InMemoryDataRepository();

  @Before
  public void initMocks() {
    repo.client = mock(BackpackApiClient.class);
    repo.parser = mock(Parser.class);

    Item item = new Item(e -> {
      e.name = "metal";
      e.qualities = new HashSet<>();
      e.qualities.add(new Quality("Unique"));
    });
    when(repo.parser.parse(null)).thenReturn(Arrays.asList(item));
  }

  @Test
  public void dataUpdatesOnlyAfterCertainAmountOfTime() {
    repo.loadAll();
    repo.loadAll();
    verify(repo.client, times(1)).getPrices();
  }

  @Test
  public void actuallyUpdatesOnDemand() {
    repo.loadAll();
    repo.dropRefreshTimer();
    repo.loadAll();
    verify(repo.client, times(2)).getPrices();
  }
}