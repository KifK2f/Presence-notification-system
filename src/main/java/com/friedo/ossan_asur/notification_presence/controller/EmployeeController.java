package com.friedo.ossan_asur.notification_presence.controller;


import com.friedo.ossan_asur.notification_presence.model.ActionType;
import com.friedo.ossan_asur.notification_presence.model.Employee;
import com.friedo.ossan_asur.notification_presence.model.Presence;
import com.friedo.ossan_asur.notification_presence.repository.EmployeeRepository;
import com.friedo.ossan_asur.notification_presence.repository.PresenceRepository;
import com.friedo.ossan_asur.notification_presence.service.serviceImpl.EmployeeService;
import com.friedo.ossan_asur.notification_presence.service.serviceImpl.PresenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PresenceService presenceService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PresenceRepository presenceRepository;

    @GetMapping("/employees")
    public List<Employee> listeEmploye(){
        return employeeService.showEmployees();
    }

    @GetMapping("/employee/id/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getOneEmployeeById(id);
    }


    @GetMapping("/employee/matricule/{matricule}")
    public ResponseEntity<?> getEmployeeByMatricule(@PathVariable String matricule) {
        Optional<Employee> employeeOpt = employeeRepository.findByMatricule(matricule);
        if (employeeOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matricule introuvable.");
        }
        return ResponseEntity.ok(employeeOpt.get());
    }


    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee){
        return employeeService.saveOneEmployee(employee);
    }

//    -------------------------

    @PostMapping("/employee/presence")
    public ResponseEntity<?> markPresence(@RequestBody Map<String, String> request) {
        String matricule = request.get("matricule");
        String action = request.get("action");

        Optional<Employee> employeeOpt = employeeRepository.findByMatricule(matricule);

        if (employeeOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matricule introuvable.");
        }

        Employee employee = employeeOpt.get();

        Presence presence = new Presence();
        presence.setEmployee(employee);
        presence.setDate(LocalDate.now());
        presence.setHour(LocalTime.now());
        presence.setActionType(ActionType.valueOf(action)); // ARRIVEE or DEPART

        presenceRepository.save(presence);

        return ResponseEntity.ok("Présence enregistrée avec succès.");
    }

//    -------------------

    @GetMapping("/employee/matricule/{matricule}/check-presence")
    public ResponseEntity<?> checkPresenceStatus(@PathVariable String matricule) {
        Optional<Employee> employeeOpt = employeeRepository.findByMatricule(matricule);

        if (employeeOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matricule introuvable.");
        }

        Employee employee = employeeOpt.get();
        boolean hasPresenceToday = presenceRepository.existsByEmployeeIdAndDate(employee.getId(), LocalDate.now());

        Map<String, Object> response = new HashMap<>();
        response.put("employee", employee);
        response.put("hasPresenceToday", hasPresenceToday);

        return ResponseEntity.ok(response);
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
