package hello.employee;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeRepository {

    // Így lesz szálbiztos
    private List<Employee> employees =
            Collections.synchronizedList(new ArrayList<>());

    private AtomicLong i = new AtomicLong();

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employees.add(employee);
            employee.setId(i.incrementAndGet());
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
