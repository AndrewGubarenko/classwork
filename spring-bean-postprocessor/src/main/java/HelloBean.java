public class HelloBean {
  @RandomInt(min = 10, max = 100)
  private int randomValue1;

  @RandomInt(min = 100, max = 1000)
  private Integer randomValue2;

  private void init() {
    System.out.println("HelloBean init");
    System.out.println("Generated random value1: " + randomValue1);
    System.out.println("Generated random value2: " + randomValue2);
    //randomValue1 = ThreadLocalRandom.current().nextInt(10, 100);
    //randomValue2 = ThreadLocalRandom.current().nextInt(100, 1000);
  }

  public int getPrimitiveValue() {
    return randomValue1;
  }

  public Integer getBoxedValue() {
    return randomValue2;
  }
}
