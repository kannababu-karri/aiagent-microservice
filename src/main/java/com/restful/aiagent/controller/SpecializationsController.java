package com.restful.aiagent.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.aiagent.entities.Specializations;
import com.restful.aiagent.service.SpecializationsService;
import com.restful.aiagent.utils.ILConstants;

@RestController
@RequestMapping("/api/specializations")
@CrossOrigin(origins = { ILConstants.ANGULAR_URL_DEV, ILConstants.ANGULAR_URL_PROD,
		ILConstants.ANGULAR_URL_PROD_HTTPS })
public class SpecializationsController {

    private final SpecializationsService specializationsService;
    
    public SpecializationsController(SpecializationsService specializationsService) {
    	this.specializationsService = specializationsService;
    }

    @GetMapping
    public List<Specializations> getAll() {
        return specializationsService.getAllSpecializations();
    }

    @GetMapping("/{id}")
    public Specializations getById(@PathVariable Long id) {
        return specializationsService.getById(id);
    }
}
