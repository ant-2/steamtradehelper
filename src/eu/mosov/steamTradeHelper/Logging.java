package eu.mosov.steamTradeHelper;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Logging {
  private final String log4jSystemPropertyName = "log4j.configurationFile";
  private String filePath;

  public Logging(String filePath) {
    this.filePath = filePath;
  }

  public void configure() {
    Path path = Paths.get(filePath);
    Path realPath;
    try {
      realPath = path.toRealPath();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    System.setProperty(
        log4jSystemPropertyName,
        realPath.toString()
    );
  }
}
