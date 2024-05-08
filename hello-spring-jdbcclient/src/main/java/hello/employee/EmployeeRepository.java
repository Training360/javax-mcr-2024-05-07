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

    private JdbcClient jdbcClient;

    public Employee save(Employee employee) {



        if (employee.getId() == null) {
            var keyHolder = new GeneratedKeyHolder();
            var sql = "insert into employees(emp_name) values (?)";
            jdbcClient.sql(sql).update(keyHolder, "id");
            employee.setId((Long) keyHolder.getKeys().get("id"));
            return employee;
        }
        else {
            var sql = """
                    update 
                    employees set emp_name = ? 
                    where id = ?""";

            // Update
            jdbcClient.sql(sql).update();
            return employee;
        }
    }

    public List<Employee> findAll() {
        return jdbcClient.sql("select id, emp_name from employees")
                .query((rs, row) -> new Employee(rs.getLong("id"), rs.getString("emp_name")))
                .list();
    }

    public Optional<Employee> findById(long id) {
        try {
            return Optional.of(jdbcClient.sql("select id, emp_name from employees where id = ?id")
                    .param("id", id).query(
                            (rs, row) -> new Employee(rs.getLong("id"), rs.getString("emp_name")
                    )).single());
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteById(long id) {
        jdbcClient.sql("delete from employee where id = ?id").param(id).update();
    }
}
