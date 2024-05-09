package training.configdemo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties("environments")
public class EnvironmentsConfig {

    private Map<String, Environment> instances;
}
