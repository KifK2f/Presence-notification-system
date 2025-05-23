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

    LocalDate today = LocalDate.now();

    // ARRIVEE spécifique : autorisée uniquement si aucune présence aujourd’hui
    if (presence.getActionType() == ActionType.ARRIVEE) {
        boolean hasArrivee = presenceRepository.existsByEmployeeIdAndActionTypeAndDate(employeeId, ActionType.ARRIVEE, today);
        if (hasArrivee) {
            throw new RuntimeException("Vous avez déjà une ARRIVEE enregistrée aujourd'hui.");
        }
    }

    // DEPART
    if (presence.getActionType() == ActionType.DEPART) {
        boolean hasDepart = presenceRepository.existsByEmployeeIdAndActionTypeAndDate(employeeId, ActionType.DEPART, today);
        if (hasDepart) {
            throw new RuntimeException("Vous avez déjà un DEPART enregistré aujourd'hui.");
        }
    }

    // SORTIE_PAUSE
    if (presence.getActionType() == ActionType.SORTIE_PAUSE) {
        boolean hasSortiePause = presenceRepository.existsByEmployeeIdAndActionTypeAndDate(employeeId, ActionType.SORTIE_PAUSE, today);
        if (hasSortiePause) {
            throw new RuntimeException("Vous avez déjà une SORTIE_PAUSE enregistrée aujourd'hui.");
        }
    }

    // RETOUR_PAUSE
    if (presence.getActionType() == ActionType.RETOUR_PAUSE) {
        boolean hasRetourPause = presenceRepository.existsByEmployeeIdAndActionTypeAndDate(employeeId, ActionType.RETOUR_PAUSE, today);
        if (hasRetourPause) {
            throw new RuntimeException("Vous avez déjà un RETOUR_PAUSE enregistré aujourd'hui.");
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
