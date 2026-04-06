package com.restful.aiagent.entities;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
@EqualsAndHashCode(of = "doctorId")
public class Doctors {

	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "doctor_id", columnDefinition = "CHAR(36)")
    private String doctorId;

    private String name;

    private int experience;

    // Mapping to specialization
    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specializations specialization;
    
    // One doctor has many slots
    @OneToMany(mappedBy = "doctors", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Slots> slots;

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Specializations getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specializations specialization) {
		this.specialization = specialization;
	}

	public List<Slots> getSlots() {
		return slots;
	}

	public void setSlots(List<Slots> slots) {
		this.slots = slots;
	}
}
