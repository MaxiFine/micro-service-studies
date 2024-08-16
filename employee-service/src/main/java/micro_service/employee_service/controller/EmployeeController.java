package micro_service.employee_service.controller;

import micro_service.employee_service.model.Employee;
import micro_service.employee_service.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    private EmployeeRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee added: {}", employee);
        return  repository.addDepartment(employee);
    }

    @GetMapping
    public List<Employee> findAllDepartment(){
        LOGGER.info("Employee find all");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        LOGGER.info("Employee Find: id={}", id);
        return repository.findById(id);
    }


    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return repository.findByDepartmentId(departmentId);
    }


}
