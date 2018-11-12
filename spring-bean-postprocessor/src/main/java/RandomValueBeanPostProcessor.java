import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class RandomValueBeanPostProcessor implements BeanPostProcessor {

  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {

    // todo should work for any bean. not only helloBean
    if (beanName.equals("helloBean")) {
//      ((HelloBean)bean).setRandomValue(42);

      System.out.println("Hoooray");

      Class<?> aClass = bean.getClass();
      Field[] declaredFields = aClass.getDeclaredFields();

      Field f = declaredFields[0];

//      Annotation[] annotations = f.getAnnotations();
//      RandomInt a = (RandomInt) annotations[0];

      RandomInt a = f.getAnnotation(RandomInt.class);
      // todo check for null?

      int min = a.min();
      int max = a.max();

      int random = ThreadLocalRandom.current().nextInt(min, max);

      f.setAccessible(true);
      try {
        f.setInt(bean, random);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return bean;
  }

}
