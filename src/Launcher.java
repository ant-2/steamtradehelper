import org.eclipse.jetty.server.NetworkTrafficServerConnector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URISyntaxException;

public class Launcher {
	public static void main(String[] args) throws Exception {
//		log4j2Config("config\\log4j2.xml");

		Server server = new Server();

		NetworkTrafficServerConnector connector = new NetworkTrafficServerConnector(server);
		connector.setPort(8080);
		server.addConnector(connector);

		WebAppContext context = new WebAppContext("webapp", "/");

		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			// fix for Windows, so Jetty doesn't lock files
			context.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
		}

		server.setHandler(context);
		server.start();
	}

	static void log4j2Config(String path) throws URISyntaxException {
		System.setProperty("log4j.configurationFile", path);
	}
}
