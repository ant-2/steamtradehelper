package eu.mosov.steamtradehelper.data;

import eu.mosov.steamtradehelper.model.DataPostProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryItemRepo<K, V, D> {
  private Map<K, V> mem;
  private DataPostProcessor<K, V, D> dataPostProcessor;

  public InMemoryItemRepo(DataPostProcessor<K, V, D> dataPostProcessor) {
    this.mem = new HashMap<>();
    this.dataPostProcessor = dataPostProcessor;
  }

  public void processData(D data) {
    Map<K, V> parsed = dataPostProcessor.parse(data);
    mem.putAll(parsed);
  }

  public V get(K key) {
    return mem.get(key);
  }
}
