package com.restful.aiagent.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.aiagent.entities.Slots;

@Repository
public interface SlotsRepository extends JpaRepository<Slots, Long> {
    List<Slots> findByDoctors_DoctorIdAndSlotDateAndIsBookedFalse(String doctorId, LocalDate date);
}
