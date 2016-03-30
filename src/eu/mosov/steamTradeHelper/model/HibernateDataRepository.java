package eu.mosov.steamTradeHelper.model;

import eu.mosov.steamTradeHelper.client.BackpackApiClient;
import eu.mosov.steamTradeHelper.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateDataRepository implements DataRepository {
	@Autowired HibernateOperations hibernate;
	@Autowired BackpackApiClient client;

	@Override
	public List<Item> getCurrencies() {
		return hibernate.loadAll(Item.class);
	}

	@Override
	public List<Item> getPrices() {
		return hibernate.loadAll(Item.class);
	}

	public void updateBase() {
		if (getCurrencies().size() == 0) {
			insertData();
		} else {
			updateCurrencies();
		}
	}

	List<Item> updateCurrencies() {
		List<Item> itemsFromBackpack = client.getCurrencies();
		List<Item> itemsFromDb = hibernate.loadAll(Item.class);
		if (itemsFromDb.size() == 0) {
			itemsFromBackpack.forEach(hibernate::save);
		} else {
			for (Item e : itemsFromBackpack) {
				hibernate.execute((HibernateCallback<Item>) session -> {
					session.createQuery("update Item set description = :desk where name = :name")
						 .setParameter("desk", "desk")
						 .setParameter("name", e.getName())
						 .executeUpdate();
					return null;
				});
			}
		}
		return hibernate.loadAll(Item.class);
	}

	void insertData() {
		List<Item> items = client.getCurrencies();
		items.forEach(hibernate::persist);
	}
}
