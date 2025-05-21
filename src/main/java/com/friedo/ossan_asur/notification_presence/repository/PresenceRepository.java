package com.friedo.ossan_asur.notification_presence.repository;

import com.friedo.ossan_asur.notification_presence.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenceRepository extends JpaRepository<Presence, Long> {
}
