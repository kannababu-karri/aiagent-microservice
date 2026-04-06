package com.restful.aiagent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restful.aiagent.entities.Specializations;
import com.restful.aiagent.repository.SpecializationsRepository;

@Service
public class SpecializationsService {

    private final SpecializationsRepository specializationsRepository;
    
    public SpecializationsService(SpecializationsRepository specializationsRepository) {
    	this.specializationsRepository = specializationsRepository;
    }

    public List<Specializations> getAllSpecializations() {
        return specializationsRepository.findAll();
    }

    public Specializations getById(Long id) {
        return specializationsRepository.findById(id).orElseThrow(() -> new RuntimeException("Specializations not found"));
    }
}
