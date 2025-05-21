package com.friedo.ossan_asur.notification_presence.repository;

import com.friedo.ossan_asur.notification_presence.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // 1. Pour trouver un employé par son matricule exact
    // Retourne un Optional<Employee> car un employé peut ne pas exister avec ce matricule.
    Optional<Employee> findByMatricule(String matricule);

    // 2. Pour trouver tous les employés dont le matricule contient une certaine chaîne (insensible à la casse)
    List<Employee> findByMatriculeContainingIgnoreCase(String keyword);

    // 3. Pour trouver un employé par son prénom exact
    // Retourne un Optional<Employee> car un employé peut ne pas exister avec ce prénom.
    Optional<Employee> findByFirstName(String firstName);

    // 4. Pour trouver un employé par son nom de famille exact
    Optional<Employee> findByLastName(String lastName);

    // 5. Pour trouver tous les employés ayant un prénom ou un nom de famille donné
    List<Employee> findByFirstNameOrLastName(String firstName, String lastName);

    // 6. Pour trouver tous les employés dont le prénom contient une certaine chaîne (insensible à la casse)
    List<Employee> findByFirstNameContainingIgnoreCase(String keyword);

    // 7. Pour trouver tous les employés dont le nom de famille contient une certaine chaîne (insensible à la casse)
    List<Employee> findByLastNameContainingIgnoreCase(String keyword);

    // 8. Pour trouver un employé par son prénom ET son nom de famille exacts
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

}
