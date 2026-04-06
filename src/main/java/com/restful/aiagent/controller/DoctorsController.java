package com.restful.aiagent.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.aiagent.entities.DoctorsDTO;
import com.restful.aiagent.service.DoctorsService;
import com.restful.aiagent.utils.ILConstants;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = { ILConstants.ANGULAR_URL_DEV, ILConstants.ANGULAR_URL_PROD,
		ILConstants.ANGULAR_URL_PROD_HTTPS })
public class DoctorsController {

    private final DoctorsService doctorsService;
    
    public DoctorsController(DoctorsService doctorsService) {
    	this.doctorsService = doctorsService;
    }

    @GetMapping("/by-specialization/{id}")
    public List<DoctorsDTO> getDoctors(@PathVariable Long id) {
        return doctorsService.getBySpecialization(id);
    }
}
