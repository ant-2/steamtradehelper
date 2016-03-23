package eu.mosov.steamTradeHelper.entity.parser;

import eu.mosov.steamTradeHelper.entity.Item;
import eu.mosov.steamTradeHelper.entity.SimpleItem;

import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParserImpl implements Parser<Item> {

	@Override
	public List<Item> parseCurrencies(JsonObject data) {
		List<Item> list = new ArrayList<>();
		JsonObject curr = data.getJsonObject("response").getJsonObject("currencies");
		for (Map.Entry<String, JsonValue> e : curr.entrySet()) {
			list.add(new SimpleItem(e.getKey()));
		}
		return list;
	}

	@Override
	public List<Item> parsePrices(JsonObject data) {
		List<Item> list = new ArrayList<>();
		JsonObject items = data.getJsonObject("response").getJsonObject("items");
		for (Map.Entry<String, JsonValue> e : items.entrySet()) {
			list.add(new SimpleItem(e.getKey()));
		}
		return list;
	}
}
