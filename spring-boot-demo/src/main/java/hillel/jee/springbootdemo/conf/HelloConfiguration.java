package hillel.jee.springbootdemo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration {
  @Bean
  public Object hello2() {
    return new Object();
  }
}
