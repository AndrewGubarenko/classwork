package hillel.jee.beans.services;

import java.util.concurrent.ThreadLocalRandom;

public class AvatarService {

  private final String[] emojis;

  public AvatarService(String[] emojis) {
    this.emojis = emojis;
  }

  public String getRandomOne() {
    int index = ThreadLocalRandom.current().nextInt(emojis.length);

    return emojis[index];
  }
}
