package com.restful.aiagent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restful.aiagent.entities.Patients;

public interface PatientsRepository  extends JpaRepository<Patients, String> {
	Optional<Patients> findByEmail(String email);
}
