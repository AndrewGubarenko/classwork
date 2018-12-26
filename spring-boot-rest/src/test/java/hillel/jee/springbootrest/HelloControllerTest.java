package hillel.jee.springbootrest;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hillel.jee.springbootrest.configs.AppDescriptionConfig;
import hillel.jee.springbootrest.controllers.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AppDescriptionConfig config;

  @Test
  public void appDescriptionAsGreeting() throws Exception {
    when(config.getName()).thenReturn("my-app-name");
    when(config.getDescription()).thenReturn("my-app-description");

    mockMvc.perform(get("/hello"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.name").value("my-app-name"))
        .andExpect(jsonPath("$.description").value("my-app-description"))
        ;//.andDo(print());
  }
}
