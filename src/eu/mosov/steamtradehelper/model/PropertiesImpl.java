package eu.mosov.steamtradehelper.model;

import java.util.function.Consumer;

public class PropertiesImpl {
  public int quality;
  public String tradable;
  public String craftable;

  public PropertiesImpl(Consumer<PropertiesImpl> builder) {
    builder.accept(this);
  }
}

class Test {
  public static void main(String[] args) {
    PropertiesImpl properties = new PropertiesImpl(e -> {

    });
  }
}
