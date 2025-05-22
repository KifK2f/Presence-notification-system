package com.friedo.ossan_asur.notification_presence.service.serviceImpl;

import com.friedo.ossan_asur.notification_presence.model.ActionType;
import com.friedo.ossan_asur.notification_presence.model.Employee;
import com.friedo.ossan_asur.notification_presence.model.Presence;
import com.friedo.ossan_asur.notification_presence.repository.EmployeeRepository;
import com.friedo.ossan_asur.notification_presence.repository.PresenceRepository;
import com.friedo.ossan_asur.notification_presence.service.IPresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class PresenceService implements IPresenceService {
    @Autowired
    private PresenceRepository presenceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Presence> showPresences() {
        return presenceRepository.findAll();
    }


@Override
public Presence saveOnePresence(Presence presence) {
    presence.setDate(LocalDate.now());
    presence.setHour(LocalTime.now());

    Long employeeId = presence.getEmployee().getId();

    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Aucun employé avec l'ID : " + employeeId));

    presence.setEmployee(employee);

    // Vérifie si l'employé a déjà une présence aujourd'hui
    boolean hasPresenceToday = presenceRepository.existsByEmployeeIdAndDate(employeeId, LocalDate.now());

    if (!hasPresenceToday) {
        presence.setActionType(ActionType.ARRIVEE);
    } else {
        if (presence.getActionType() == null) {
            throw new RuntimeException("Veuillez spécifier une action différente de ARRIVEE.");
        }

        if (presence.getActionType() == ActionType.ARRIVEE) {
            throw new RuntimeException("L'employé a déjà une ARRIVEE aujourd'hui. Veuillez choisir une autre action.");
        }
    }

    return presenceRepository.save(presence);
}



    @Override
    public Presence getOnePresence(Long id) {
        return presenceRepository.findById(id).get();
    }

    @Override
    public void deletePresence(Long id) {
        presenceRepository.deleteById(id);
    }

}
