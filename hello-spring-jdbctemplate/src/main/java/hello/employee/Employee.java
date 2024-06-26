package hello.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    private Long id;

    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
