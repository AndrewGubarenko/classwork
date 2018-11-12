import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:app.xml")
public class AppTests {
  @Autowired
  private HelloBean helloBean;

  @Test
  public void promitiveGeneratedValueShouldBeWithinBounds() throws Exception {
    assertThat(helloBean.getPrimitiveValue(), is(
        both(
            greaterThanOrEqualTo(10)
        ).and(
            lessThanOrEqualTo(100))
    ));
  }

  @Test
  public void boxedGeneratedValueShouldBeWithinBounds() throws Exception {
    assertThat(helloBean.getBoxedValue(), is(
        both(
            greaterThanOrEqualTo(100)
        ).and(
            lessThanOrEqualTo(1000))
    ));
  }
}
