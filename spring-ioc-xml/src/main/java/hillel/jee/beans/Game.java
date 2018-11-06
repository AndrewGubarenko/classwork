package hillel.jee.beans;

import hillel.jee.beans.players.Player;
import hillel.jee.beans.services.AvatarService;
import java.util.concurrent.TimeUnit;

public class Game {

  private AvatarService avatarService;

  private Player human;
  private Player computer;

  public Game() {
    System.out.println("-----------------");
    System.out.println("Creating the game");
  }

  public Game(AvatarService avatarService) {

    this.avatarService = avatarService;
  }

  public void setHuman(Player human) {
    System.out.printf("Choosing player %s [%s]\n", human, human.getAvatar());

    this.human = human;
  }

  public void setComputer(Player computer) {
    System.out.printf("Choosing player %s [%s]\n", computer, computer.getAvatar());

    this.computer = computer;
  }

  public void startGame() {
    String refereeAvatar = avatarService.getRandomOne();

    System.out.printf("Choosing the referee... [%s]\n", refereeAvatar);
    System.out.printf("[%s] says: Starting the game...\n", refereeAvatar);

    computer.move();
    human.move();
  }

  @Override
  public String toString() {
    return "Game{" +
        "human=" + human +
        ", computer=" + computer +
        '}';
  }

  private void initGame() throws InterruptedException {
    System.out.println("-----------------");
    System.out.print("Game initialization");

    for (int i = 0; i < 10; i++) {
      System.out.print('.');
      TimeUnit.MILLISECONDS.sleep(500);
    }
    System.out.println();
  }

  private void destroyGame() throws Exception {
    System.out.println("-----------------");
    System.out.println("Finishing the game...");

    TimeUnit.SECONDS.sleep(1);

    System.out.println("\uD83D\uDCA5 BOOOOOOOM \uD83D\uDCA5");
    System.out.println();
  }
}
