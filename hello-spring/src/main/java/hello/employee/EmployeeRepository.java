package hello.employee;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeRepository {

    // Így lesz szálbiztos
    private List<Employee> employees =
            Collections.synchronizedList(new ArrayList<>());

    private AtomicLong i = new AtomicLong();

    public void save(Employee employee) {
        employees.add(employee);
        employee.setId(i.incrementAndGet());
    }

    public List<Employee> findAll() {
        return employees;
    }
}
