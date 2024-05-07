package hello.employee;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping( "/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResourceDto> save(@RequestBody EmployeeResourceDto employee,
                                         UriComponentsBuilder builder) {
        employeeService.save(employee);

        return ResponseEntity
                .created(builder.path("/api/employees/{id}").buildAndExpand(employee.getId()).toUri())
                .header("Response-Id", UUID.randomUUID().toString())
                .body(employee);
    }

    @GetMapping
    public List<EmployeeResourceDto> findAll() {
        return employeeService.findAll();
    }
}
