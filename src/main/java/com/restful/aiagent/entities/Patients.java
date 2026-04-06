package com.restful.aiagent.entities;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "patients")
@Data
public class Patients {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "patient_id", columnDefinition = "CHAR(36)")
    private String patientId;

    private String name;

    private String email;

    // One patient can have multiple appointments
    @OneToMany(mappedBy = "patients", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointments> appointments;
}
