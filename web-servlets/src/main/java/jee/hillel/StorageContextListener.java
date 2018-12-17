package jee.hillel;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StorageContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    sce.getServletContext().setAttribute("userStorage", new UserStorage());
  }
}
