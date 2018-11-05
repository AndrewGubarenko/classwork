package hillel.jee;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class GameConfiguration {
//  @Bean
//  public Game game() {
//    return new Game(human(), computer());
//  }

  @Bean
  @Primary
  @Scope(SCOPE_PROTOTYPE)
  public Player human() {
    return new Human();
  }

  @Bean
//  @Scope(SCOPE_SINGLETON)
  public Player computer() {
    return new Computer();
  }

}
