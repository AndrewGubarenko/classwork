import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.Test;

public class A_Lambdas {

  @Test
  public void anonymousClass() {
    Reader reader = new Reader() {
      @Override
      public String read() {
        return "hello_reader";
      }
    };

    String str = reader.read();

    assertThat(str).isEqualTo("hello_reader");
  }

  @Test
  public void lambda() {
    Reader reader = () -> "hello_reader";
    Something som = () -> "hello_reader";

    Supplier<String> sup = () -> "hello_sup";

    String str = reader.read();

    assertThat(sup.get())
        .isEqualTo("hello_sup");

    assertThat(str).isEqualTo("hello_reader");
  }

  @Test
  public void name111() {
    foo("hello_reader" + "1" + "2");

    foo(() -> "hello_reader" + "1" + "2");
  }

  int ii = 20;
  static int iii = 30;

  @Test
  public void closure(A_Lambdas this) {
    int i = 10;
    int iiii[] = { 10 };

    Reader reader = new Reader() {
      @Override
      public String read() {
        return "hello_reader" + i;
      }
    };

    Reader reader1 = () -> "hello_reader" + i + "_" + ii + "_" + iii;

    Reader reader2 = () -> {
//      i = i + 20;
      iiii[0] = 1000;

      ii += 20;
      iii += 20;
      return "hello_reader" + i + "_" + ii + "_" + iii + "_" + iiii[0];
    };

    System.out.println(reader.read());
    System.out.println(reader1.read());
    System.out.println(reader2.read());

  }

  @Test
  public void methodReference() {
    Reader reader1 = () -> "";
    Reader reader2 = this::getString;
    Reader reader3 = A_Lambdas::getStringStatic;

    Reader reader4 = String::new;
  }

  private String getString() {
    return "";
  }

  static private String getStringStatic() {
    return "";
  }

  private void foo(String str) {

  }

  private void foo(Reader reader) {
    reader.read();
  }

  @FunctionalInterface
  interface Reader {
    String read();
  }

  @FunctionalInterface
  interface Something {
    String something();
  }

  @FunctionalInterface
  interface Writer<T> {
    void write(T data);
  }

  @FunctionalInterface
  interface ReadWriter<T, R> {
    R readWrite(T data);
  }

  @Test
  public void writerTest() {
    Writer<String> writer = data -> {};
    ReadWriter readWriter = data -> data;

    Supplier<String> sup = () -> "";
    Consumer<String> con = data -> {};
    Function<String, Integer> fun = (String str) -> 10;
  }

  @Test
  public void clearTheList() {
    Consumer<List<String>> consumer = list -> list.clear();

    List<String> list = new ArrayList<>();
    list.add("hello");

    consumer.accept(list);

    assertThat(list).isEmpty();
  }

  @Test
  public void forEach() {
    List.of("one", "two", "three")
        .forEach(str -> System.out.println(str));
  }
}
