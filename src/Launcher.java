import eu.mosov.steamTradeHelper.App;
import eu.mosov.steamTradeHelper.Config;
import eu.mosov.steamTradeHelper.Logging;

public class Launcher {
  public static void main(String[] args) throws Exception {
    // logging must be configured before all other classes, cause some classes may already use loggers and may accidentally initialize log4j container without a log4j configuration file
    new Logging("config\\log4j2.xml").configure();

    Config config = new Config("config\\config.properties");
    App app = new App(config);
    app.startApplication();
  }
}
