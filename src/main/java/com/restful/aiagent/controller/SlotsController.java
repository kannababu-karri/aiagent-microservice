package com.restful.aiagent.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restful.aiagent.entities.SlotsDto;
import com.restful.aiagent.service.SlotsService;
import com.restful.aiagent.utils.ILConstants;

@RestController
@RequestMapping("/api/slots")
@CrossOrigin(origins = { ILConstants.ANGULAR_URL_DEV, ILConstants.ANGULAR_URL_PROD,
		ILConstants.ANGULAR_URL_PROD_HTTPS })
public class SlotsController {

    private final SlotsService slotsService;
    
    public SlotsController(SlotsService slotsService) {
    	this.slotsService = slotsService;
    }

    @GetMapping
    public List<SlotsDto> getSlots(
        @RequestParam String doctorId,
        @RequestParam String date) {
        return slotsService.getAvailableSlots(doctorId, LocalDate.parse(date));
    }
}
