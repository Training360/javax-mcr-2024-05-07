package hello.employee;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface EmployeeRepository extends
        ListCrudRepository<Employee, Long>
{

}
