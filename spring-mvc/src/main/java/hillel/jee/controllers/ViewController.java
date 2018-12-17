package hillel.jee.controllers;

import hillel.jee.entities.Student;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/views")
public class ViewController {
  @GetMapping("/")
  public String index() {
    return "example.html";
  }

  @PostMapping(path = "/students", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView showStudentInfo(@RequestBody Student student) {
    ModelAndView model = new ModelAndView("example.html");
    model.addObject("student", student);

    return model;
  }

  @PostMapping(path = "/students2", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String showStudentInfo2(@RequestBody Student student, Model model) {
    model.addAttribute("student", student);

    return "example.html";
  }
}
