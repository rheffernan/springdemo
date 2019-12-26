package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//deprecated:
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
//note: this version of the hateos library does not have the deprecated classes from the tutorial


@RestController
class EmployeeController {

    private final EmployeeRepository repository;
    //private Logger logger = Logger.getLogger(EmployeeController.class.getName());

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("employees")
    List<Employee> all() {
        return repository.findAll();
    }

    @GetMapping("/estream")
    List<Employee> efilter() {
        //return repository.findAll().stream().filter(e -> e.getId() == 2).collect(Collectors.toList());
        return repository.findAll().stream().filter(e -> e.getId() > 0).collect(Collectors.toList());
    }

    @GetMapping("/max")
    Employee maxId() {
        //return repository.findAll().stream().filter(e -> e.getId() == 2).collect(Collectors.toList());
        return repository.findAll().stream().max(Comparator.comparingLong(Employee::getId)).get();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.saveAndFlush(newEmployee);
    }

    // Single item (HATEOAS version)
    /*@GetMapping("/employees/{id}")
    Resource<Employee> one(@PathVariable Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return new Resource<>(employee,
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }*/

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}