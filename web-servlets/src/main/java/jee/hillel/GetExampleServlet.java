package jee.hillel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get-method-example")
public class GetExampleServlet extends AppServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, String> vars = new HashMap<>(){{
        put("varName", "John Doe");
    }};

    renderTemplate(resp, req.getServletPath()/*, vars*/);
  }
}
