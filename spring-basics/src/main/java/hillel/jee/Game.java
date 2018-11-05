package hillel.jee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Game {

  private Player human;
  private Player computer;

//  @Autowired
  public Game(Player human, @Qualifier("computer") Player computer) {
    this.human = human;
    this.computer = computer;
  }

//  @Autowired
//  public void setHuman(Player human) {
//    this.human = human;
//  }
//
//  @Autowired
//  public void setComputer(Player computer) {
//    this.computer = computer;
//  }

  public void startGame() {

  }

  @Override
  public String toString() {
    return "Game{" +
        "human=" + human +
        ", computer=" + computer +
//        ", something=" + something +
        '}';
  }
}
