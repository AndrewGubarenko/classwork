package hillel.jee.controllers;

import hillel.jee.entities.Student;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class IndexController {
  @RequestMapping(path = "/", method = RequestMethod.GET)
  @ResponseBody
  public String index() {
    return "hello worm";
  }

  @RequestMapping(path = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String echo(@RequestBody String body) {
    return body;
  }

  @RequestMapping(
      path = "/params",
      method = RequestMethod.GET
  )
  @ResponseBody
  public String params(@RequestParam String name, @RequestParam int age) {
    return String.format("Received a student %s of age %d", name, age);
  }

  @RequestMapping(
      path = "/params",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  @ResponseBody
  public String paramsPost(@RequestParam Map<String, String> params) {
    return String.format("Received a student %s of age %s", params.get("name"), params.get("age"));
  }

  @PostMapping("/students")
  public ResponseEntity<List<Student>> students() {
    return ResponseEntity.ok(
        List.of(
            new Student("John Doe", 21),
            new Student("Jane Roe", 22),
            new Student("Josh Bloe", 20)
        )
    );
  }

  @PostMapping("/students2")
  public @ResponseBody List<Student> students2() {
    return List.of(
        new Student("John Doe", 21),
        new Student("Jane Roe", 22),
        new Student("Josh Bloe", 20)
    );
  }

  @GetMapping("/students3")
  public @ResponseBody Student students3() {
    return new Student("John Doe", 21);
  }

  @PostMapping("/students4")
  public @ResponseBody Student students4(@RequestBody Student student) {
    return new Student("Mr. " + student.getName(), student.getAge());
  }

  @PostMapping("/students5/{id}")
  public @ResponseBody Student students5(@RequestBody Student student, @PathVariable String id) {
    return new Student("Mr. " + student.getName() + ", ID: " + id, student.getAge());
  }
}
