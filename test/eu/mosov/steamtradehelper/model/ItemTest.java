package eu.mosov.steamtradehelper.model;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemTest {
  Item item = new Item("A hat");

  @Test
  public void hasOwnProperty() throws Exception {
    item.addProperty("quality", "6", "11");

    assertTrue(item.hasOwnProperty("quality", "6", "11"));
    assertFalse(item.hasOwnProperty("quality", "0"));
    assertTrue(item.hasOwnProperty("Quality", "6"));
  }

  @Test
  public void keysIgnoresLetterCase() throws Exception {
    item.addProperty("KEY");
    item.addProperty("KeY");
    item.addProperty("key");

    assertThat(item.propertiesGroupsCount, is(1));
  }
}