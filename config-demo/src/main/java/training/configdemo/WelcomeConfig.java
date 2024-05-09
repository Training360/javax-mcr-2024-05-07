package training.configdemo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("welcome")
public class WelcomeConfig {

    private String text;
}
