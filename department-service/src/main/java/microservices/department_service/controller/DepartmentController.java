package microservices.department_service.controller;


import microservices.department_service.client.EmployeeClient;
import microservices.department_service.model.Department;
import microservices.department_service.repositories.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

//    logger object
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(DepartmentRepository repository) {
        this.repository = repository;
    }
    @Autowired
    private EmployeeClient employeeClient;
    private DepartmentRepository repository;


    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return  repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAllDepartment(){
        LOGGER.info("Department find all");
            return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        LOGGER.info("Department Find: id={}", id);
        return repository.findById(id);
    }
}
