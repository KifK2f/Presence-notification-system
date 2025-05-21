package com.friedo.ossan_asur.notification_presence.service.serviceImpl;

import com.friedo.ossan_asur.notification_presence.model.Presence;
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

    @Override
    public List<Presence> showPresences() {
        return presenceRepository.findAll();
    }

    @Override
    public Presence saveOnePresence(Presence presence) {
        presence.setDate(LocalDate.now());
        presence.setHour(LocalTime.now());
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
