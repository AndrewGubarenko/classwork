import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class AppTests {
  @Test
  public void dummy() throws Exception {
    System.out.println("Hello Worm!");
//    PrintStream out = new FilePrintStream("/tmp/out");
    //System.setOut(out);

    System.out.println("Hello World!");

    System.out.println(System.out);
  }
}



