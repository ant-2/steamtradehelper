package eu.mosov.steamtradehelper.model;

import eu.mosov.steamtradehelper.model.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateDataRepository implements DataRepository {
  @Autowired
  HibernateOperations hibernate;

  @Override
  public Item saveOrUpdate(Item item) {
    hibernate.saveOrUpdate(item);
    return item;
  }

  @Override
  public List<Item> saveOrUpdateAll(List<Item> list) {
    for (Item item : list) {
      hibernate.saveOrUpdate(item);
    }
    return list;
  }

  @Override
  public List<Item> getAllItems() {
    return hibernate.loadAll(Item.class);
  }

  @Override
  public Item find(String itemName) {
    return null;
  }
}

/*	//todo убрать вставку данных в базу, и найти куда ее запихнуть
    @PostConstruct
	void updateBase() {
		if (getCurrencies().size() == 0) {
			insertData();
		} else {
			updateCurrencies();
		}
	}

	//todo потупить на тему как оптимизировать апдейт итемов
	List<Item> updateCurrencies() {
		List<Item> itemsFromBackpack = client.getCurrencies();
		List<Item> itemsFromDb = hibernate.loadAll(Item.class);
		if (itemsFromDb.size() == 0) {
			itemsFromBackpack.forEach(hibernate::saveOrUpdate);
		} else {
			for (Item e : itemsFromBackpack) {
				hibernate.execute((HibernateCallback<Item>) session -> {
					session.createQuery("update Item set description = :desk where name = :name")
						 .setParameter("desk", "desk")
						 .setParameter("name", e.getQualityName())
						 .executeUpdate();
					return null;
				});
			}
		}
		return hibernate.loadAll(Item.class);
	}

	void insertData() {
		List<Item> items = client.getCurrencies();
		items.forEach(hibernate::saveOrUpdate);
	}*/