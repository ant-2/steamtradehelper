
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Enables transfer entities to Json for returning by methods in Resource classes
 *
 * */
@Provider
public class PojoJsonMapperProvider implements ContextResolver<ObjectMapper> {

	ObjectMapper defaultObjectMapper;

	public PojoJsonMapperProvider(ObjectMapper defaultObjectMapper) {
		this.defaultObjectMapper = defaultObjectMapper;
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return null;
	}

	private static ObjectMapper createDefaultMapper() {
		return new ObjectMapper();
	}
}
