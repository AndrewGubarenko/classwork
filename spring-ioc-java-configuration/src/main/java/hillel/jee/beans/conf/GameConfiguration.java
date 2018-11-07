package hillel.jee.beans.conf;

import hillel.jee.beans.Game;
import hillel.jee.beans.services.AvatarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

  @Bean
  public Game game(AvatarService avatarService) {
    return new Game(avatarService);
  }

  @Bean
  public AvatarService avatarService(
      AvatarService avatarServiceBackedByArray,
      AvatarService avatarServiceBackedByProperties,
      @Value("${avatar.service:default}") String avatarServiceName) {

    switch (avatarServiceName) {
      case "array":
        return avatarServiceBackedByArray;
      case "properties":
        return avatarServiceBackedByProperties;
      default:
        return avatarServiceBackedByArray;
    }
  }

  @Bean
  public AvatarService avatarServiceBackedByArray() {
    return new AvatarService(emojis());
  }

  @Bean
  public AvatarService avatarServiceBackedByProperties(@Value("${avatars}") String[] emojis) {
    return new AvatarService(emojis);
  }

  @Bean
  public String[] emojis() {
    return new String[] {
      "\uD83D\uDC16", "\uD83D\uDC01", "\uD83D\uDC02",
        "\uD83D\uDC07", "\uD83D\uDC09", "\uD83D\uDC0B"
    };
  }
}
