package eu.mosov.steamTradeHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
  private static final Logger log = LoggerFactory.getLogger(Config.class);
  private Path path;

  public Config(String path){
    this.path = Paths.get(path);
  }

  public String readProperty(String key) {
    String result = loadConfigFromDisk().getProperty(key);
    if (result == null) {
      throw new RuntimeException("A Config instance tried to read property with name: "+key+", but result is null. Looks like there is no such property in the configuration file.");
    }
    return result;
  }

  public String apiKey() {
    String key = loadConfigFromDisk().getProperty("backpack.tf-api-key");
    if (key == null)  throw new RuntimeException("No Backpack.tf API key are found in application config file.");
    return key;
  }

  private Properties loadConfigFromDisk() throws RuntimeException {
    Properties config = new Properties();
    InputStream in = null;
    try {
      in = Files.newInputStream(path);
      config.load(in);
    } catch (IOException e) {
      RuntimeException exception = new RuntimeException("Error while reading the config file. Path to file: "+path, e);
      log.error(exception.getMessage(), exception);
      throw exception;
    } finally {
      closeStreamQuietly(in);
    }
    return config;
  }

  private static void closeStreamQuietly(InputStream inputStream) {
    try {
      inputStream.close();
    } catch (IOException e) {
      log.error("Can't close InputStream.", e);
    }
  }
}