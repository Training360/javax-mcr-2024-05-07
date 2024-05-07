package hello.employee;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping( "/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee,
                                         UriComponentsBuilder builder) {
        employeeService.save(employee);

        return ResponseEntity
                .created(builder.path("/api/employees/{id}").buildAndExpand(employee.getId()).toUri())
                .body(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
}
