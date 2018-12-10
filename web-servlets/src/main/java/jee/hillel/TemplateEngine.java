package jee.hillel;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;

public class TemplateEngine {

  private final ServletContext servletContext;
  private String layout;

  public static final String REGEX_START = Pattern.quote("{{");
  public static final String REGEX_END = Pattern.quote("}}");
  public static final Pattern PATTERN = Pattern.compile(REGEX_START + "\\s+content\\s+" + REGEX_END);

  public TemplateEngine(ServletContext servletContext) {
    this.servletContext = servletContext;
    try {
      byte[] layoutBytes = servletContext
          .getResourceAsStream("/WEB-INF/templates/layouts/default.htm")
          .readAllBytes();
      this.layout = new String(layoutBytes);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public String getTemplate(String templateName) {
    byte[] templateBytes = new byte[0];
    try {
      templateBytes = servletContext
          .getResourceAsStream(String.format("/WEB-INF/templates/views/%s.htm", templateName))
          .readAllBytes();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return PATTERN.matcher(new String(layout)).replaceAll(new String(templateBytes));
  }

  public String getLayout() {
    return layout;
  }
}
