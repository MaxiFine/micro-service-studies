package micro_service.employee_service.repository;

import micro_service.employee_service.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {



    private List<Employee> employees = new ArrayList<>();

    public Employee addDepartment(Employee department) {
        employees.add(department);
        return department;
    }

    public Employee findById(String id) {
        return employees.stream()
                .filter(employee ->
                        employee.id().equals(id))
                .findFirst()
                .orElseThrow(() ->  new RuntimeException("Id does not exist."));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public List<Employee> findByDepartmentId(Long departmentId) {
        return employees.stream()
                .filter(d ->
                        d.departmentId().equals(departmentId))
                .toList();
//                .orElseThrow(() -> new RuntimeException("Id not found."));
    }

}
