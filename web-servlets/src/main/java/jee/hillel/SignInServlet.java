package jee.hillel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sign-in")
public class SignInServlet extends AppServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    renderTemplate(resp, "sign-in");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    UserStorage userStorage = (UserStorage) req.getServletContext().getAttribute("userStorage");
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    if (userStorage.userExists(username) &&
        userStorage.getUserPassword(username) != null &&
        userStorage.getUserPassword(username).equals(password)) {
      HttpSession session = req.getSession(true);
      session.setAttribute("username", username);

      resp.sendRedirect("/my-app/");
    } else {
      resp.sendRedirect("/my-app/sign-in");
    }
  }
}
