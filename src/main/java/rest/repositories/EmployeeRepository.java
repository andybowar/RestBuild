package rest.repositories;

import org.springframework.data.repository.CrudRepository;
import rest.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);
}
