package hello.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeResourceDto save(EmployeeResourceDto employee) {
        var entity = new Employee(employee.getName());
        employeeRepository.save(entity);
        return new EmployeeResourceDto(entity.getId(), entity.getName());
    }

    public List<EmployeeResourceDto> findAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(entity -> new EmployeeResourceDto(entity.getId(), entity.getName()))
                .toList();
    }

    public EmployeeResourceDto findById(long id) {
        return employeeRepository
                .findById(id)
                .map(entity -> new EmployeeResourceDto(entity.getId(), entity.getName()))
                .orElseThrow(
                        () -> new IllegalArgumentException("Employee not found with id %d".formatted(id)));
    }
}
