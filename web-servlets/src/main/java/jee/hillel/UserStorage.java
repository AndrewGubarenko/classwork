package jee.hillel;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
  private Map<String, String> users = new HashMap<>();
  {
    users.put("admin", "admin");
  }

  public boolean userExists(String username) {
    return users.containsKey(username);
  }

  public String getUserPassword(String username) {
    return users.get(username);
  }
}
