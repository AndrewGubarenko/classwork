package hillel.jee.springbootdemo;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication

public class SpringBootDemoApplication {

  @RequestMapping("/")
  public String get() {
    return "Hello worm!";
  }

  @RequestMapping("/test")
  public String getTest() {
    return "Hello world!";
  }

  @Profile("!test")
  @Bean
  public Object hello() {
    return new Object();
  }


  public static void main(String[] args) {
    //SpringApplication.run(SpringBootDemoApplication.class, args);

    SpringApplication springApplication = new SpringApplication(SpringBootDemoApplication.class);
    ConfigurableApplicationContext context = springApplication.run(args);

    System.out.println(context.getBean("hello"));

    Arrays.stream(context.getBeanDefinitionNames())
        .forEach(System.out::println);
  }
}
