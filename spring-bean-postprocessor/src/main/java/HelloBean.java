import java.util.concurrent.ThreadLocalRandom;

public class HelloBean {
  @RandomInt(min = 10, max = 100)
  private int randomValue;

  @RandomInt(min = 100, max = 1000)
  private int randomValue2;

  private void init() {
    System.out.println("HelloBean init");
    System.out.println("Generated random value: " + randomValue);
    //randomValue = ThreadLocalRandom.current().nextInt(10, 100);
  }

  public int getValue() {
    return randomValue;
  }
}
