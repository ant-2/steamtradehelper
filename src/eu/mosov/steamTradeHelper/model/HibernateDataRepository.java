package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.model.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOperations;

import java.util.List;

public class HibernateDataRepository implements DataRepository {
  @Autowired HibernateOperations hibernate;

  @Override
  public List<Item> loadAll() {
    return hibernate.loadAll(Item.class);
  }
}