package eu.mosov.steamtradehelper.data;

import eu.mosov.steamtradehelper.model.Item;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

interface ItemRepository {

  Item getItem(String name);

  List<Item> getAllItems();

  List<Item> getItemsWithAttribute(String attributeGroupName, List<String> attributes);

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
