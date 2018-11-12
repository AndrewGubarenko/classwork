import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:/app.xml");
    context.registerShutdownHook();

    context.getBean(HelloBean.class);
  }


}
