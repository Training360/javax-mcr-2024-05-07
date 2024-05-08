package hello.employee;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@AllArgsConstructor
public class EmployeeRepository {

    private JdbcTemplate jdbcTemplate;

    // Így lesz szálbiztos
    private final List<Employee> employees =
            Collections.synchronizedList(new ArrayList<>());

    private final AtomicLong i = new AtomicLong();

    public Employee save(Employee employee) {
        var sql = "insert into employees(emp_name) values (?)";

        var keyHolder = new GeneratedKeyHolder();
        if (employee.getId() == null) {
            jdbcTemplate.update(c -> {
                var ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, employee.getName());
                        return ps;
                    }
                    , keyHolder);
            employee.setId((Long) keyHolder.getKeys().get("id"));
            return employee;
        }
        else {
            // Update
            var found = findById(employee.getId());
            if (found.isPresent()) {
                found.get().setName(employee.getName());
                return found.get();
            }
            else {
                return null;
            }
        }
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Optional<Employee> findById(long id) {
        return employees.stream()
                .filter(employee -> employee.getId()==id)
                .findAny();
    }

    public void deleteById(long id) {
        employees.removeIf(employee -> employee.getId() == id);
    }
}
