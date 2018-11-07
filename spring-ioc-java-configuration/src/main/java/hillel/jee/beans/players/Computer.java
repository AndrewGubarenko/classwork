package hillel.jee.beans.players;

public class Computer implements Player {

  private final String avatar = "\uD83D\uDE08";

  @Override
  public void move() {
    System.out.printf("[%s] says: I'm a blazing fast! Be afraid of me\n", avatar);
  }

  @Override
  public String getAvatar() {
    return avatar;
  }
}
