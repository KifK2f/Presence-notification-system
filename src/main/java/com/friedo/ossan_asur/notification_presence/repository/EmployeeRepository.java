package com.friedo.ossan_asur.notification_presence.repository;

import com.friedo.ossan_asur.notification_presence.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
