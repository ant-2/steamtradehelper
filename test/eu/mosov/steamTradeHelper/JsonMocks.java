package eu.mosov.steamtradehelper;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

/**
 * Builder to obtain json mocks for test classes
 * */
public class JsonMocks {
	public enum Mocks {
		CURRENCIES,
		PRICES
	}

	private static JsonBuilderFactory f = Json.createBuilderFactory(null);

	public static JsonObject getMock(Mocks mock) {
		switch (mock) {
			case CURRENCIES:
				return f.createObjectBuilder().add("response",
					 f.createObjectBuilder().add("currencies",
						  f.createObjectBuilder().add("metal", "description"))
				).build();

			case PRICES:
				return f.createObjectBuilder().add("response",
					 f.createObjectBuilder().add("items",
						  f.createObjectBuilder().add("metal", "description"))
				).build();

			default:
				return f.createObjectBuilder().add("default object", "in JsonMocks").build();
		}
	}
}

