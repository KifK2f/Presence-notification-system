package com.friedo.ossan_asur.notification_presence.controller;


import com.friedo.ossan_asur.notification_presence.model.Employee;
import com.friedo.ossan_asur.notification_presence.service.serviceImpl.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> listeEmploye(){
        return employeeService.showEmployees();
    }

    @GetMapping("/employee/id/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getOneEmployeeById(id);
    }


    @GetMapping("/employee/matricule/{matricule}")
    public Employee getEmployeeByMatricule(@PathVariable String matricule){
        return employeeService.getOneEmployeeByMatricule(matricule);
    }

//
//    @GetMapping("/employee/{id}")
//    public Employee getEmployee(@PathVariable Long id){
//        return employeeService.getOneEmployee(id);
//    }
//
//
//    @GetMapping("/employee/{id}")
//    public Employee getEmployee(@PathVariable Long id){
//        return employeeService.getOneEmployee(id);
//    }
//
//
//    @GetMapping("/employee/{id}")
//    public Employee getEmployee(@PathVariable Long id){
//        return employeeService.getOneEmployee(id);
//    }


    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee){
        return employeeService.saveOneEmployee(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee){
        employee.setId(id);
        return employeeService.saveOneEmployee(employee);
    }

    @DeleteMapping("/employee/{id}")
    public void supprimerEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }




}
