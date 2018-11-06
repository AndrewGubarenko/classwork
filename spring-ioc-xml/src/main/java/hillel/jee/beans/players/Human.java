package hillel.jee.beans.players;

import java.util.concurrent.TimeUnit;

public class Human implements Player {

  private Integer thinkingTime;
  private String avatar;

  @Override
  public void move() {
    System.out.printf("[%s] says: Thinking slow", avatar);

    int iterationTime = thinkingTime / 10;
    for (int i = 0; i < 10; i++) {
      sleep(iterationTime);
      System.out.print('.');
    }

    System.out.println(" I'm just a human ¯\\_(ツ)_/¯");
  }

  @Override
  public String getAvatar() {
    return avatar;
  }

  private void sleep(int sleepTime) {
    try {
      TimeUnit.MILLISECONDS.sleep(sleepTime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void setThinkingTime(Integer thinkingTime) {
    this.thinkingTime = thinkingTime;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
