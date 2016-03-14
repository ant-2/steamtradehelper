import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class JerseyApplication extends ResourceConfig {
	static String basePackage = "eu.mosov.steamTradeHelper";
	public JerseyApplication() {
		packages(basePackage);
	}
}
