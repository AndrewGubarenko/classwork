package hillel.jee.beans.conf;

import hillel.jee.beans.players.Computer;
import hillel.jee.beans.players.Human;
import hillel.jee.beans.players.Player;
import hillel.jee.beans.services.AvatarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan("hillel.jee.beans")
@Import(GameConfiguration.class) // if you decide to comment this line, you should uncomment the one above
@PropertySource(value = "/application.properties", encoding="UTF-8")
//@PropertySource("/application.properties") // won't work correctly with unicode characters. As alternative you can escape unicode characters - as an example @see Computer.avatar field
public class PlayersConfiguration {

  @Bean
  public Player human(AvatarService avatarService,
                      @Value("${human.thinking.time.ms}") Integer thinkingTimeMs) { // SpEL "#{new Integer('${human.thinking.time.ms}')}"

    Human human = new Human();

    human.setThinkingTime(thinkingTimeMs);
    human.setAvatar(avatarService.getRandomOne());

    return human;
  }

  @Bean
  public Player computer() {
    return new Computer();
  }
}
