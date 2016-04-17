package eu.mosov.steamtradehelper.model.entity.parser;

import eu.mosov.steamtradehelper.model.entity.Item;

import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemParser<E extends Item> implements Parser<Item> {

	@Override
	public List<Item> parse(JsonObject data) {
		List<Item> list = new ArrayList<>();
		JsonObject curr = data.getJsonObject("response").getJsonObject("currencies");
		for (Map.Entry<String, JsonValue> e : curr.entrySet()) {
			list.add(new Item(e.getKey()));
		}
		return list;
	}
}

