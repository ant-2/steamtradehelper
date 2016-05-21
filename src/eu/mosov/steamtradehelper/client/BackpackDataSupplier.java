package eu.mosov.steamtradehelper.client;

import org.springframework.stereotype.Service;

import javax.json.JsonObject;

/**
 * Получает данные с сайта Backpack.tf
 * */
@Service
public class BackpackDataSupplier {
  RestClient client = new RestClient();

  JsonObject getPrices() {
    return client.getResourceAsType("", JsonObject.class);
  }
}