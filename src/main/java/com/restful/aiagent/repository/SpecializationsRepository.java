package com.restful.aiagent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.aiagent.entities.Specializations;

@Repository
public interface SpecializationsRepository extends JpaRepository<Specializations, Long> {
    // Optional custom queries if needed
}
