package com.friedo.ossan_asur.notification_presence.service;

import com.friedo.ossan_asur.notification_presence.model.Presence;

import java.util.List;

public interface IPresenceService {

    public List<Presence> showPresences();

    public Presence saveOnePresence(Presence presence);

    public Presence getOnePresence(Long id);

    public void deletePresence(Long id);

}
