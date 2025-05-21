package com.friedo.ossan_asur.notification_presence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Column(name = "heure")
    private LocalTime hour;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_mouvement", nullable = false)
    private Movement movement;

    @ManyToOne //Many presence To One Employee
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
