package eu.mosov.steamTradeHelper.client;

import eu.mosov.steamTradeHelper.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObject;
import java.util.HashMap;

public class BackpacktfDevApi {
  private static final Logger log = LoggerFactory.getLogger(BackpacktfDevApi.class);
  private RestClient restClient;
  private String apiKey;  //todo добавить проверку надействительность апи ключа
  private HashMap<String, JsonObject> repo = new HashMap<>();

  public BackpacktfDevApi() {}  // todo разобраться, почему не будет работать если убрать пустой конструктор

  public BackpacktfDevApi(Config config, RestClient client) {
    this.apiKey = config.apiKey();
    restClient = client;
  }

  public JsonObject getPrices() {
    String uri = BackpackDevApiUrlsEnum.PRICES.toString();

    if (!repo.containsKey("prices")) {
      JsonObject result = loadResource(uri);
      repo.put("prices", loadResource(uri));
      return result;
    }
    return repo.get("prices");
  }

  private JsonObject loadResource(String url) {
    try {
      return restClient.getResourceAsType(
          buildQueryPath(url),
          JsonObject.class
      );
    } catch (Exception e) {
      String msg = "Can't load resource in uri: "+url;
      log.error(msg, e);
      throw new RuntimeException(msg, e);
    }
  }

  private String buildQueryPath(String url) {
    return url+"/?key="+this.apiKey;
  }

  private enum BackpackDevApiUrlsEnum {
    PRICES("http://backpack.tf/api/IGetPrices/v4");

    BackpackDevApiUrlsEnum(String uri) {
      this.uri = uri;
    }

    private String uri;

    @Override
    public String toString() {
      return this.uri;
    }
  }
}