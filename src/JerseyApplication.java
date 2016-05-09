import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.json.stream.JsonGenerator;
import javax.ws.rs.ApplicationPath;

/**
 * Jersey application subclass, needs for Jersey v2 configuration
 */
@ApplicationPath("rest")
public class JerseyApplication extends ResourceConfig {
  static String basePackage = "eu.mosov.steamTradeHelper";

  public JerseyApplication() {
    packages(basePackage);
    register(PojoJsonMapperProvider.class);
    register(JacksonFeature.class)
        .property(JsonGenerator.PRETTY_PRINTING, true);
  }
}
