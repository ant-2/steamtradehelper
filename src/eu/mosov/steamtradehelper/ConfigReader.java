package eu.mosov.steamtradehelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
  private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);
  private Properties properties = new Properties();
  private String path;

  public ConfigReader(String path){
    this.path = path;
    this.properties = loadConfigFromDisk(this.path);
  }

  public void setConfigPath(String path) {
    this.path = path;
  }

  public String getBackpackApiKey() {
    loadConfigFromDisk(this.path);
    String key = properties.getProperty("backpack-tf_api-key");
    if (key == null)  throw new RuntimeException("No Backpack.tf API key are found in application config file.");
    return key;
  }

  private static Properties loadConfigFromDisk(String path) {
    Properties config = new Properties();
    FileInputStream in = null;
    try {
      in = new FileInputStream(path);
      config.load(in);
    } catch (FileNotFoundException e) {
      log.error("App config file are not found. Path to file: "+path, e);
    } catch (IOException e) {
      log.error("Error in attempt to read app config file. Path to file: "+path, e);
    } finally {
      closeQuietly(in);
    }
    return config;
  }

  private static void closeQuietly(InputStream inputStream) {
    try {
      inputStream.close();
    } catch (IOException e) {
      log.error("Exception in closing InputStream in ConfigReader. ", e);
    }
  }
}
