package jee.hillel;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AppServlet extends HttpServlet {

  private TemplateEngine templateEngine;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    templateEngine = (TemplateEngine) getServletContext().getAttribute("templateEngine");

    super.service(req, resp);
  }

  public void renderTemplate(HttpServletResponse resp, String templateName) {
    try {
      PrintWriter writer = resp.getWriter();
      writer.print(templateEngine.getTemplate(templateName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
