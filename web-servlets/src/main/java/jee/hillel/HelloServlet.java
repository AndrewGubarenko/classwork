package jee.hillel;

import java.io.IOException;
import java.io.Writer;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello2015")
public class HelloServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Writer writer = resp.getWriter();
    writer.write("No luck this time!");
  }
}
