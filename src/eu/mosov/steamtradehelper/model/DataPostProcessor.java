package eu.mosov.steamtradehelper.model;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface DataPostProcessor<K, V, D> {

  public Map<K, V> parse(D data);

  default <X, Y> void processElements(
                                               Iterable<X> source,
                                               Predicate<X> tester,
                                               Function<X, Y> mapper,
                                               Consumer<Y> block) {
    for (X p : source) {
      if (tester.test(p)) {
        Y data = mapper.apply(p);
        block.accept(data);
      }
    }
  }
}
