package com.friedo.ossan_asur.notification_presence.repository;

import com.friedo.ossan_asur.notification_presence.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PresenceRepository extends JpaRepository<Presence, Long> {
    boolean existsByEmployeeIdAndDate(Long employeeId, LocalDate date);
}
