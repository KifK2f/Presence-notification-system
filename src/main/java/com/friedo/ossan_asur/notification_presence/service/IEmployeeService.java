package com.friedo.ossan_asur.notification_presence.service;

import com.friedo.ossan_asur.notification_presence.model.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> showEmployees();

    public Employee saveOneEmployee(Employee employee);

    public Employee getOneEmployeeById(Long id);

    public Employee getOneEmployeeByMatricule(String matricule);

    public void deleteEmployee(Long id);

}
