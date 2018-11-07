package hillel.jee.beans;

import hillel.jee.beans.players.Player;
import hillel.jee.beans.services.AvatarService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class Game implements InitializingBean, DisposableBean {

  private AvatarService avatarService;

  private Player human;
  private Player computer;

//  @see Game.afterPropertiesSet method
//  public Game() {
//    System.out.println("-----------------");
//    System.out.println("Creating the game");
//  }

  @Autowired
  public Game(AvatarService avatarService) {
    this.avatarService = avatarService;
  }

  @Autowired
  public void setHuman(Player human) {
    System.out.printf("Choosing player %s [%s]\n", human, human.getAvatar());

    this.human = human;
  }

  @Autowired
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

  private void createGame() {
    System.out.println("-----------------");
    System.out.println("Creating the game");
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

  @Override // as an alternative you can define destroy method when configuring game bean with @Bean. @see Bean.destroyMethod
  public void destroy() throws Exception {
    destroyGame();
  }

  @Override // as an alternative you can define initMethod method when configuring game bean with @Bean. @see Bean.initMethod
  public void afterPropertiesSet() throws Exception {
    createGame();
    initGame();
  }
}
