package hillel.jee.springbootrest.controllers;

import hillel.jee.springbootrest.configs.AppDescriptionConfig;
import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//  @Value("${my-app.name}")
//  private String appName;
//
//  @Value("${my-app.description}")
//  private String appDescription;

  @Autowired
  private AppDescriptionConfig appDescription;

  @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Description hello() {
    return new Description(appDescription.getName(), appDescription.getDescription());
  }

  @Data
//  @AllArgsConstructor
//  @NoArgsConstructor
  public static class Description {
    @NotEmpty
    String name;
    String description;

    public Description(String name, String description) {
      if (name.isBlank()) {
        throw new ValidationException("name cannot be blank");
      }
      this.name = name;
      this.description = description;
    }
  }
}
