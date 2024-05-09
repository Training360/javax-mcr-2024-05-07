package hello.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeResourceDto save(EmployeeResourceDto employee) {
        var entity = new Employee(employee.getName(), employee.getSalary());
        employeeRepository.save(entity);
        return new EmployeeResourceDto(entity.getId(), entity.getName(), entity.getSalary());
    }

    public List<EmployeeResourceDto> findAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(entity -> new EmployeeResourceDto(entity.getId(), entity.getName(), entity.getSalary()))
                .toList();
    }

    public EmployeeResourceDto findById(long id) {
        return employeeRepository
                .findById(id)
                .map(entity -> new EmployeeResourceDto(entity.getId(), entity.getName(), entity.getSalary()))
                .orElseThrow(
                        () -> new IllegalArgumentException("Employee not found with id %d".formatted(id)));
    }

    public EmployeeResourceDto update(EmployeeResourceDto employeeResourceDto) {
        var employee = employeeRepository.findById(employeeResourceDto.getId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Employee not found with id %d".formatted(employeeResourceDto.getId())));
        employee.setName(employeeResourceDto.getName());
        employeeRepository
                .save(employee);
        return new EmployeeResourceDto(employee.getId(), employee.getName(), employee.getSalary());
    }

    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeResourceDto> findByName(String filter) {
        return employeeRepository
                .findByNameLike(filter + "%")
                .stream()
                .map(entity -> new EmployeeResourceDto(entity.getId(), entity.getName(), entity.getSalary()))
                .toList();
    }
}
