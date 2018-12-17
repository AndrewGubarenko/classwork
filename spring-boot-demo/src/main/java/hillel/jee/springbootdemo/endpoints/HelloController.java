package hillel.jee.springbootdemo.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @RequestMapping("/ololo")
  public String ololo() {
    return "NLO";
  }
}
