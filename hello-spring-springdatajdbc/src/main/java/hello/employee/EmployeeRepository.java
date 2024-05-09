package hello.employee;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface EmployeeRepository extends
        ListCrudRepository<Employee, Long>
{

    List<Employee> findByNameLike(String name);
}
