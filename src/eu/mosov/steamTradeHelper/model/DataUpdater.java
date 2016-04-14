package eu.mosov.steamTradeHelper.model;

import java.util.List;

/**
 * Retries data from backpack.tf site and updates data in a database
 *
 * */
public interface DataUpdater<Item> {

	void updateData(List<Item> items) throws Exception;
}
