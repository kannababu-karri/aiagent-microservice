package com.restful.aiagent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.aiagent.entities.Doctors;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, String> {
    List<Doctors> findBySpecializationId(Long specializationId);
}
