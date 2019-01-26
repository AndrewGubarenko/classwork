package hillel.jee.security.config;

import static hillel.jee.security.domain.Role.RoleName.*;

import hillel.jee.security.domain.User;
import hillel.jee.security.services.UserService;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataConfig implements CommandLineRunner {

  private final UserService userService;

  public DefaultDataConfig(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void run(String... args) throws Exception {
    createDefaultRoles();
    createDefaultUsers();
  }

  private void createDefaultRoles() {
    Arrays.stream(values())
        .forEach(userService::createRole);
  }

  private void createDefaultUsers() {
    List.of(
        User.builder().username("john").password("123").build(),
        User.builder().username("jane").password("123").build()
    ).forEach(userService::createUser);

    userService.createUser(
        User.builder().username("admin").password("admin").build(),
        ADMIN
    );

    userService.createUser(
        User.builder().username("bill").password("bill.the.butcher").build(),
        MODERATOR
    );

    userService.createUser(
        User.builder().username("umoderator").password("12345").build(),
        MODERATOR, USER
    );
  }

}
