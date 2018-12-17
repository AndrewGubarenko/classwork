package jee.hillel;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

  private ServletContext context;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.context = filterConfig.getServletContext();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    String currentPath = req.getRequestURI();
    HttpSession session = req.getSession();
    if (!currentPath.contains("sign-in") && session.getAttribute("username") == null) {
      res.sendRedirect(req.getContextPath() + "/sign-in");
    } else {
      res.getWriter().println("<!-- Powered by filter -->");
      chain.doFilter(request, response);
    }
  }
}
