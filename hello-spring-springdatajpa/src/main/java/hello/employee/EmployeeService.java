package hello.employee;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeResourceDto save(EmployeeResourceDto employee) {
        var entity = new Employee(employee.getName(), employee.getAddresses().stream().map(a ->
                new Address(a.getCity())).toList());

        log.info("Save: {}", entity);

        employeeRepository.save(entity);
        return new EmployeeResourceDto(entity.getId(), entity.getName(),
                entity.getAddresses().stream().map(a -> new AddressResourceDto(a.getId(), a.getCity())).toList()
        );
    }

    public List<EmployeeResourceDto> findAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(entity -> new EmployeeResourceDto(entity.getId(), entity.getName(),
                        entity.getAddresses().stream().map(a -> new AddressResourceDto(a.getId(), a.getCity())).toList()
                ))
                .toList();
    }

    public EmployeeResourceDto findById(long id) {
        return employeeRepository
                .findById(id)
                .map(entity -> new EmployeeResourceDto(entity.getId(), entity.getName(),
                        entity.getAddresses().stream().map(a -> new AddressResourceDto(a.getId(), a.getCity())).toList()
                ))
                .orElseThrow(
                        () -> new IllegalArgumentException("Employee not found with id %d".formatted(id)));
    }

    @Transactional
    public EmployeeResourceDto update(EmployeeResourceDto employeeResourceDto) {
        var employee = employeeRepository.findById(employeeResourceDto.getId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Employee not found with id %d".formatted(employeeResourceDto.getId())));
        employee.setName(employeeResourceDto.getName());
// JPA-ban NEM KELL SEMMIT HÍVNI UPDATE-KOR!
        // FŐLEG NEM EZT:
        //        employeeRepository
//                .save(employee);
        return new EmployeeResourceDto(employee.getId(), employee.getName(),
                employee.getAddresses().stream().map(a -> new AddressResourceDto(a.getId(), a.getCity())).toList()
        );
    }

    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }
}
