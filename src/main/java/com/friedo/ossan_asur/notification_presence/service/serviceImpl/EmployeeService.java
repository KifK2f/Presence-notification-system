package com.friedo.ossan_asur.notification_presence.service.serviceImpl;

import com.friedo.ossan_asur.notification_presence.model.Employee;
import com.friedo.ossan_asur.notification_presence.repository.EmployeeRepository;
import com.friedo.ossan_asur.notification_presence.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> showEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveOneEmployee(Employee employee) {
        if (employee.getId() == null){
            employee.setMatricule
                    (
                            UUID.randomUUID().toString().substring(0,4).toUpperCase()
                                    +employee.getLastName().substring(0,2).toUpperCase()
                                    +employee.getFirstName().substring(0,2).toUpperCase()
                    );
        }
        return employeeRepository.save(employee);
    }
    /*Initialize your Date however you like it.
            Date date = new Date();
    Calendar calendar = new GregorianCalendar();
calendar.setTime(date);
    int year = calendar.get(Calendar.YEAR);
    //Add one to month {0 - 11}
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    */
    /*UUID.randomUUID() : Génère un identifiant unique universel.
     toString() : Convertit le UUID en une chaîne de caractères.
    substring(0, 4) : Extrait les 4 premiers caractères.
    toUpperCase() : Met les caractères en majuscules*/
    //Universal Unique Identifier = UUID
    /*L'abréviation UUID signifie Universal Unique IDentifier ,
     parfois également appelé GUID (Globally Unique IDentifier).
     Il s'agit d'un entier de 128 bits utilisé pour l'identification
     des données dans les systèmes informatiques.*/

    @Override
    public Employee getOneEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee getOneEmployeeByMatricule(String matricule) {
        return employeeRepository.findByMatricule(matricule).get();
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
