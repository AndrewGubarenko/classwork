package hillel.jee.springbootrest.configs;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "my-app")
public class AppDescriptionConfig {
  private String name;
  @NotEmpty
  private String description;
}
