package com.restful.aiagent.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.restful.aiagent.entities.Slots;
import com.restful.aiagent.entities.SlotsDto;
import com.restful.aiagent.repository.SlotsRepository;

@Service
public class SlotsService {

    private final SlotsRepository slotsRepository;
    
    public SlotsService(SlotsRepository slotsRepository) {
    	this.slotsRepository = slotsRepository;
    }

    public List<SlotsDto> getAvailableSlots(String doctorId, LocalDate date) {
        //return slotsRepository.findByDoctors_DoctorIdAndSlotDateAndIsBookedFalse(doctorId, date);
        List<Slots> slots = slotsRepository
                .findByDoctors_DoctorIdAndSlotDateAndIsBookedFalse(doctorId, date);

            return slots.stream()
                .map(s -> new SlotsDto(
                    s.getSlotId(),
                    s.getSlotDate(),
                    s.getStartTime(),
                    s.getEndTime()
                ))
                .toList();
    }
}
