package eu.mosov.steamTradeHelper;

import org.eclipse.jetty.server.NetworkTrafficServerConnector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private Config config;
  private Server server;

  private static final Logger log = LoggerFactory.getLogger(App.class);

  public App(Config config) {
    this.config = config;
  }

  public void startApplication() throws Exception {
    configureServer();
    server.start();
  }

  private void configureServer() {
    server = new Server();
    NetworkTrafficServerConnector connector = new NetworkTrafficServerConnector(server);
    connector.setPort(Integer.valueOf(config.readProperty("server-port")));
    server.addConnector(connector);

    WebAppContext context = new WebAppContext("webapp", "/");

    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
      // fix for Windows, so Jetty doesn't lock files
      context.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
    }
    server.setHandler(context);
  }
}
