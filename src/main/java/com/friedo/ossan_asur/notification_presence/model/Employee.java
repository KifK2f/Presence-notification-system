package com.friedo.ossan_asur.notification_presence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employe",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"nom", "prenom"}, // Spécifier colonne
                        name = "UK_employee_name") // Ici c'est le Nom de la contrainte pour la DB (optionnel),UK= Abbreviation de Unique key
        }
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    @NotBlank(message = "Le nom de famille ne peut pas être vide.")
    @Size(min = 2, message = "Le nom de famille doit contenir au moins 2 caractères.")
    private String lastName;

    @Column(name = "prenom", nullable = false)
    @NotBlank(message = "Le prénom ne peut pas être vide.")
    @Size(min = 2, message = "Le prénom doit contenir au moins 2 caractères.")
    private String firstName;

    @Column(name = "matricule", unique = true)
    private String matricule; // Champ unique pour identifier l'employé donc doit avoir la contrainte d'unicite

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", matricule='" + matricule + '\'' +
                '}';
    }
}
