package com.friedo.ossan_asur.notification_presence.service;

import com.friedo.ossan_asur.notification_presence.model.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> showEmployees();

    public Employee saveOneEmployee(Employee employee);

    public Employee getOneEmployee(Long id);

    public void deleteEmployee(Long id);

}
