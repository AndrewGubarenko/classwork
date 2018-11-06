package hillel.jee;

import hillel.jee.beans.Game;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("/application-context.xml");

    System.out.println("-----------------");
    System.out.println("[DEBUG] Registered beans:");
    Arrays.stream(context.getBeanDefinitionNames())
        .map(beanName -> "- " + beanName)
        .forEach(System.out::println);

    System.out.println("-----------------");

    Game game = context.getBean(Game.class);
    game.startGame();

    // this is here to demonstrate bean's destroy method invocation
    ((ClassPathXmlApplicationContext) context).close();
  }
}
