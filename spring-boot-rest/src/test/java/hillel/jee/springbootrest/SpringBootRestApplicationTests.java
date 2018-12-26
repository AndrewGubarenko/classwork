package hillel.jee.springbootrest;

import static org.assertj.core.api.Java6Assertions.assertThat;

import hillel.jee.springbootrest.controllers.HelloController;
import hillel.jee.springbootrest.controllers.HelloController.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test-app.properties")
public class SpringBootRestApplicationTests {

  @Test
  public void contextLoads() {
  }

  @Autowired
  HelloController helloController;

  @Test
  public void testingInIntegrationWithProperties() {
    Description description = helloController.hello();

    assertThat(description)
        .extracting(Description::getName)
        .isEqualTo("My SpringBoot application");
  }

  @LocalServerPort
  private int port;

  @Value("${spring.mvc.servlet.path}")
  private String contextPath;

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  public void testingRealRequest() {
    String serviceUrl = String.format("http://localhost:%d/%s/hello", port, contextPath);
    ResponseEntity<Description> response = restTemplate
        .getForEntity(serviceUrl, Description.class);

    Description expected = new Description("test-name", "test-description");

    assertThat(response.getBody()).isEqualTo(expected);
  }
}

