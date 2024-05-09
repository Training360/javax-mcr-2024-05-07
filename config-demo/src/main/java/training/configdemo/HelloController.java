package training.configdemo;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableConfigurationProperties({WelcomeConfig.class, EnvironmentsConfig.class})
@AllArgsConstructor
public class HelloController {

    private WelcomeConfig welcomeConfig;

    private EnvironmentsConfig environmentsConfig;

    @GetMapping("/hello")
    public String hello() {
        return welcomeConfig.getText();
    }

    @GetMapping("/environments/{environment}")
    public Environment findByName(@PathVariable String environment) {
        return environmentsConfig.getInstances().get(environment);
    }

    @GetMapping("/data/{environment}")
    public List<String> findData(@PathVariable String environment) {
        var params = environmentsConfig.getInstances().get(environment);
        var properties = new DataSourceProperties();
        properties.setUrl(params.getDatabaseUrl());
        properties.setUsername(params.getUsername());
        properties.setPassword(params.getPassword());
        var dataSource = properties.initializeDataSourceBuilder().build();
        var jdbcClient = JdbcClient.create(dataSource);

        return jdbcClient.sql("select id, emp_name from employees").query(
                (rs, rowNum) -> rs.getString("emp_name")
        ).list();
    }
}
