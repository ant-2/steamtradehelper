package eu.mosov.steamTradeHelper.entity.parser;

import javax.json.JsonObject;
import java.util.List;

public interface Parser<Item> {

	List<Item> parseCurrencies(JsonObject data);

	List<Item> parsePrices(JsonObject data);
}
