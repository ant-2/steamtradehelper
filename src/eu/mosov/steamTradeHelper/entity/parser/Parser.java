package eu.mosov.steamTradeHelper.entity.parser;

import eu.mosov.steamTradeHelper.entity.Item;

import javax.json.JsonObject;
import java.util.List;

public interface Parser<T extends Item> {

	List<T> parseCurrencies(JsonObject data);

	List<T> parsePrices(JsonObject data);
}
