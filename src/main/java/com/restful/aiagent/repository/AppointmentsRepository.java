package com.restful.aiagent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restful.aiagent.entities.Appointments;

public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {

}
