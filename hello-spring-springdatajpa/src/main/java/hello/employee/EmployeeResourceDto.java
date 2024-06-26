package hello.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResourceDto {

    private Long id;

    private String name;

    private List<AddressResourceDto> addresses;
}
