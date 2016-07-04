package eu.mosov.steamTradeHelper.web;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequestLogFilterTest {
  @Test
  public void isRegexPatternWorks() throws Exception {
    Pattern pattern = RequestLogFilter.noNeedToLogPattern;

    // must not log request for js and css files
    assertTrue(pattern.matcher("/test/objects.js").matches());
    assertTrue(pattern.matcher("/css/main.css").matches());
    // must log html and rest requests
    assertFalse(pattern.matcher("/folder/file.html").matches());
    assertFalse(pattern.matcher("/partOfUri/file").matches());
  }
}