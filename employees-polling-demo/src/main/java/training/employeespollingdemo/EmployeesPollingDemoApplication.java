package training.employeespollingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EmployeesPollingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesPollingDemoApplication.class, args);
    }

}
