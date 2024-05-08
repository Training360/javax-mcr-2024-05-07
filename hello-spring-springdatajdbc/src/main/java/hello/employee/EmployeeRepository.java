package hello.employee;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@AllArgsConstructor
public class EmployeeRepository {

    private JdbcTemplate jdbcTemplate;

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
            jdbcTemplate.update("update employees set emp_name = ? where id = ?",employee.getName(), employee.getId());
            return employee;
        }
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("select id, emp_name from employees",
                (rs, row) -> new Employee(rs.getLong("id"), rs.getString("emp_name"))
        );
    }

    public Optional<Employee> findById(long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("select id, emp_name from employees where id = ?",
                    (rs, row) -> new Employee(rs.getLong("id"), rs.getString("emp_name")),
                    id
            ));
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteById(long id) {
        jdbcTemplate.update("delete from employees where id = ?", id);
    }
}
