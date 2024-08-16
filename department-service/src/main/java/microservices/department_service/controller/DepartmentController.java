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
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Collections;
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
        System.out.println("findA:::---------------lll");
        LOGGER.info("Department find all");
            return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        System.out.println("BYID ----------------" + id);

        LOGGER.info("Department Find: id={}", id);
        return repository.findById(id);
    }

//    @GetMapping("/all")
//    public List<Department> findAll() {
//        LOGGER.info("Department find");
//        System.out.println("findA:::---------------lll");
//        return repository.findAll();
//    }

//    @GetMapping("/with-employees")
//    public List<Department> findAllWithEmployees(){
//        System.out.println("testings-------------");
//        LOGGER.info("Department find: ");
//        List<Department> departments = repository.findAll();
//        System.out.println(departments);
//        System.out.println("DEPAERTMENTS----------");
//        departments.forEach(
//                department -> department.setEmployees(
//                        employeeClient.findByDepartment(department.getId())));
//        return departments;
//    }

    // fixing tests
    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        try {
            LOGGER.info("Department find: ");
            List<Department> departments = repository.findAll();
            System.out.println("----------------tests--------");
            departments.forEach(
                    department -> {
                        try {
                            department.setEmployees(
                                    employeeClient.findByDepartment(department.getId()));
                        } catch (WebClientRequestException e) {
                            LOGGER.error("Failed to fetch employees for department {}: {}", department.getId(), e.getMessage());
                            department.setEmployees(Collections.emptyList());
                        }
                    });
            return departments;
        } catch (Exception e) {
            LOGGER.error("Error retrieving departments with employees", e);
            throw e; // Re-throw or handle appropriately
        }
    }





}
