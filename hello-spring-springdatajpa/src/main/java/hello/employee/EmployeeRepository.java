package hello.employee;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends
        JpaRepository<Employee, Long>
{

    @Query("select distinct e from Employee e left join fetch e.addresses where e.name like :name")
    public List<Employee> findEmployeeByNameLikeWithAddresses(String name);
}
