package eu.mosov.steamtradehelper.model.entity.parser;

import javax.json.JsonObject;
import java.util.List;

public interface Parser<T> {

	List<T> parse(JsonObject data);
}
