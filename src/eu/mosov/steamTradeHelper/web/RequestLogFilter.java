package eu.mosov.steamTradeHelper.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

@WebFilter("/*")
public class RequestLogFilter implements Filter {
  private Logger logger = LoggerFactory.getLogger("requestLogger");
  private String nodeId = Integer.toHexString((int) (Math.random() * 256)) + "-";
  private AtomicInteger requestId = new AtomicInteger(0);
  static final Pattern noNeedToLogPattern = Pattern.compile("(.+\\.js)|(.+\\.css)"); // doesn't loq request for javascript files and css files

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest http = (HttpServletRequest) request;

    try {
      String uri = http.getRequestURI();
      if (!noNeedToLogPattern.matcher(uri).matches()) {
        Thread.currentThread().setName(nodeId + requestId.incrementAndGet());
        logger.info(request.getRemoteAddr() + " " + http.getMethod() + " " + uri + " " + serializeParams(request));
      }

      filterChain.doFilter(request, response);
    } catch (Throwable e) {
      logger.error("Some error in RequestLogFilter", e);
    }
  }

  private String serializeParams(ServletRequest request) {
    StringBuilder builder = new StringBuilder(256);
    for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
      builder.append(param.getKey()).append("=").append(normalize(param.getValue())).append("\t");
    }
    return builder.toString();
  }

  private String normalize(String[] value) {
    String result = value.length == 1 ? value[0] : Arrays.toString(value);
    return result.replace("\r", "\\r").replace("\n", "\\n").replace("\t", "\\t");
  }

  @Override
  public void destroy() {
  }
}
