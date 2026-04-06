package com.restful.aiagent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.aiagent.entities.Appointments;
import com.restful.aiagent.entities.AppointmentsDto;
import com.restful.aiagent.service.AppointmentsService;
import com.restful.aiagent.utils.ILConstants;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = { ILConstants.ANGULAR_URL_DEV, ILConstants.ANGULAR_URL_PROD,
		ILConstants.ANGULAR_URL_PROD_HTTPS })
public class AppointmentsController {
	
	private static final Logger _LOGGER = LoggerFactory.getLogger(AppointmentsController.class);

    private final AppointmentsService appointmentsService;
    
    public AppointmentsController(AppointmentsService appointmentsService) {
    	this.appointmentsService = appointmentsService;
    }

    @PostMapping
    public Appointments book(@RequestBody AppointmentsDto request) {
    	
    	_LOGGER.info(request.toString());
    	
        return appointmentsService.book(request);
    }
}
