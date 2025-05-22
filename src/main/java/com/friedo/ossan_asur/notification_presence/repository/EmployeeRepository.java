package com.friedo.ossan_asur.notification_presence.repository;

import com.friedo.ossan_asur.notification_presence.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//     Pour trouver un employé par son matricule exact
    // Retourne un Optional<Employee> car un employé peut ne pas exister avec ce matricule.
    Optional<Employee> findByMatricule(String matricule);

}
