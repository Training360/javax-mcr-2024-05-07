package training.employeescli;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeesService implements CommandLineRunner {

    private JdbcClient jdbcClient;

    @Override
    public void run(String... args) {
        log.info("Hello World!");
        var rows = jdbcClient.sql("select emp_name from employees").query().listOfRows();
        log.info("Employees: {}, {}", rows, LocalDateTime.now());
    }
}
