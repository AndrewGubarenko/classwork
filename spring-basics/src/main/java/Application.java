import hillel.jee.Game;
import hillel.jee.Player;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@ComponentScan(basePackageClasses = Player.class)
@ComponentScan("hillel.jee")
public class Application {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
//    ApplicationContext context = new AnnotationConfigApplicationContext("hillel.jee");
//    ApplicationContext context = new ClassPathXmlApplicationContext("/app.xml");

    var game = context.getBean(Game.class);
    game.startGame();

    System.out.println(game);
//    Player p1 = context.getBean(Player.class);



//    System.out.println(p1);
//
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
//    System.out.println(Arrays.toString(beanDefinitionNames));
//
    //Arrays.stream(beanDefinitionNames).forEach(System.out::println);
////    List.of("hello").forEach(System.out::println);

    //System.out.println(game);


  }
}
