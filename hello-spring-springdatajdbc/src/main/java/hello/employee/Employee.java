package hello.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    private Long id;

    @Column("emp_name")
    private String name;

    private long salary;

    public Employee(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }
}
