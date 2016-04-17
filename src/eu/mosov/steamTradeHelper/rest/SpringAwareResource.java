package eu.mosov.steamtradehelper.rest;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

/**
 * Injects spring beans into fields which annotated with Spring annotations, like @Autowired and etc
 */
public abstract class SpringAwareResource {
  @Context
  public void setContext(ServletContext sc) {
    autowire(sc);
  }

  public void autowire(ServletContext sc) {
    autowire(sc, this);
  }

  public static void autowire(ServletContext sc, Object target) {
    XmlWebApplicationContext context = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
    context.getBeanFactory().autowireBean(target);
  }
}
