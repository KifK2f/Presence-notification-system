package com.friedo.ossan_asur.notification_presence.controller;


import com.friedo.ossan_asur.notification_presence.model.Presence;
import com.friedo.ossan_asur.notification_presence.service.serviceImpl.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presences")
@CrossOrigin("*")
public class PresenceController {

    @Autowired
    PresenceService presenceService;

    @GetMapping("/presences")
    public List<Presence> listePresence(){
        return presenceService.showPresences();
    }

    @GetMapping("/presence/{id}")
    public Presence getPresence(@PathVariable Long id){
        return presenceService.getOnePresence(id);
    }

    @PostMapping("/presences")
    public Presence savePresence(@RequestBody Presence presence){
        return presenceService.saveOnePresence(presence);
    }

    @PutMapping("/presence/{id}")
    public Presence updatePresence(@PathVariable Long id, Presence presence){
        presence.setId(id);
        return presenceService.saveOnePresence(presence);
    }

    @DeleteMapping("/presence/{id}")
    public void supprimerPresence(@PathVariable Long id){
        presenceService.deletePresence(id);
    }




}
