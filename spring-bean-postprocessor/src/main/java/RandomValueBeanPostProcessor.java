import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class RandomValueBeanPostProcessor implements BeanPostProcessor {

  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {

    Class<?> aClass = bean.getClass();
    for (Field field : aClass.getDeclaredFields()) {
      if (fieldHasCorrectType(field) && isAnnotated(field)) {
        setRandomValue(bean, field);
      }
    }

//    Arrays.stream(aClass.getDeclaredFields())
//        .filter(
//            fieldHasCorrectType()
//              .and(isAnnotated(RandomInt.class)))
//        .forEach(field -> setRandomValue(bean, field));

    return bean;
  }

  private boolean isAnnotated(Field field) {
    return field.isAnnotationPresent(RandomInt.class);
  }

  private boolean fieldHasCorrectType(Field field) {
    return field.getType().equals(int.class) || field.getType().equals(Integer.class);
  }

  private void setRandomValue(Object bean, Field field) {
    RandomInt a = field.getAnnotation(RandomInt.class);
    int min = a.min();
    int max = a.max();

    int randomValue = ThreadLocalRandom.current().nextInt(min, max);

    setFieldValue(bean, field, randomValue);
  }

  private void setFieldValue(Object bean, Field field, int randomValue) {
    field.setAccessible(true);
    try {
      if (field.getType().isPrimitive()) {
        field.setInt(bean, randomValue);
      } else {
        field.set(bean, randomValue);
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  private Predicate<Field> fieldHasCorrectType() {
    return field -> field.getType().equals(int.class) || field.getType().equals(Integer.class);
  }

  private Predicate<Field> isAnnotated(Class<? extends Annotation> clazz) {
    return field -> field.isAnnotationPresent(clazz);
  }
}
