package eu.mosov.steamtradehelper.model;

import java.util.List;

/**
 * Repository to encapsulate data that retrieved via backpack.tf API
 */
public interface DataRepository<E> {

  List<E> loadAll();
}
