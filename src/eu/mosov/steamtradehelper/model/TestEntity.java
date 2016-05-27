package eu.mosov.steamtradehelper.model;

public class TestEntity {
  public String name;
  public PropertiesImpl properties;

  public TestEntity(String name) {
    this.name = name;
  }

  public TestEntity setProperties(PropertiesImpl pr) {
    this.properties = pr;
    return this;
  }
}
