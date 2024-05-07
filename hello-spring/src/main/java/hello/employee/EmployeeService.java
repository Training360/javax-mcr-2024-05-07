package hello.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public void save(EmployeeResourceDto employee) {
        var entity = new Employee(employee.getName());
        employeeRepository.save(entity);
    }

    public List<EmployeeResourceDto> findAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(entity -> new EmployeeResourceDto(entity.getId(), entity.getName()))
                .toList();
    }
}
