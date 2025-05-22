package com.friedo.ossan_asur.notification_presence.controller;

import com.friedo.ossan_asur.notification_presence.model.ActionType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class EnumController {

//    @GetMapping("/api/enums/actions")
//    public ActionType[] getAllActionTypes() {
//        return ActionType.values();
//    }


    @GetMapping("/api/enums/actions")
    public List<ActionType> getAllActionTypes() {
        return Arrays.stream(ActionType.values())
                .filter(action -> action != ActionType.ARRIVEE) // Exclure ARRIVEE
                .collect(Collectors.toList());
    }

}
