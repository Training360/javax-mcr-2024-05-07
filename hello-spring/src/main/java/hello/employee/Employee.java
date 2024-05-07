package hello.employee;

import lombok.Data;

@Data
public class Employee {

    private Long id;

    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
