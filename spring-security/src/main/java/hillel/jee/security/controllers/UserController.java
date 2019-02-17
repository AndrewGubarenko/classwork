package hillel.jee.security.controllers;

import hillel.jee.security.domain.Role.RoleName;
import hillel.jee.security.domain.User;
import hillel.jee.security.services.SecurityService;
import hillel.jee.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  private final UserService userService;
  private final SecurityService securityService;

  @Autowired
  public UserController(UserService userService, SecurityService securityService) {
    this.userService = userService;
    this.securityService = securityService;
  }

  @GetMapping("/")
  public String welcome(Model model) {
    model.addAttribute("username", securityService.getCurrentUserUsername());

    return "welcome";
  }

  @GetMapping("/login")
  public String signInPage(Model model, String error, String logout) {
    if (error != null) {
      model.addAttribute("error", true);
    }
    if (logout != null) {
      model.addAttribute("logout", true);
    }
    return "login";
  }

  @GetMapping("/protected")
  public String protectedContent() {
    return "protected";
  }

  @GetMapping("/access-denied")
  public String accessDenied() {
    return "access-denied";
  }

  @GetMapping("/register")
  public String signUpPage() {
    return "register";
  }

  @PostMapping("/register")
  public String createUser(@Validated Model model, User userForm) {
    // todo here has to be form data validation

    if (!userService.userExists(userForm.getUsername())) {
      userService.createUser(userForm, RoleName.USER);
      securityService.loginUserAs(userForm.getUsername(), userForm.getPassword());

      return "redirect:/";
    } else {
      model.addAttribute("error", "");
      return "register";
    }
  }
}
