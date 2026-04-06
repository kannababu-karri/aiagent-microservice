package com.restful.aiagent.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.restful.aiagent.entities.Appointments;
import com.restful.aiagent.entities.AppointmentsDto;
import com.restful.aiagent.entities.Doctors;
import com.restful.aiagent.entities.Patients;
import com.restful.aiagent.entities.Slots;
import com.restful.aiagent.repository.AppointmentsRepository;
import com.restful.aiagent.repository.DoctorsRepository;
import com.restful.aiagent.repository.PatientsRepository;
import com.restful.aiagent.repository.SlotsRepository;

import jakarta.transaction.Transactional;

@Service
public class AppointmentsService {

    private final AppointmentsRepository appointmentsRepository;
    private final SlotsRepository slotsRepository;
    private final DoctorsRepository doctorsRepository;
    private final PatientsRepository patientsRepository;
    
    public AppointmentsService(AppointmentsRepository appointmentsRepository, 
    		SlotsRepository slotsRepository,
    		DoctorsRepository doctorsRepository,
    		PatientsRepository patientsRepository) {
    	this.appointmentsRepository = appointmentsRepository;
    	this.slotsRepository = slotsRepository;
    	this.doctorsRepository = doctorsRepository;
    	this.patientsRepository = patientsRepository;
    }

    @Transactional
    public Appointments book(AppointmentsDto appointmentsDto) {
    	
    	//Get slot object
        Slots slot = slotsRepository.findById(Long.valueOf(appointmentsDto.getSlotId())).orElseThrow();

        if (slot.isBooked()) {
            throw new RuntimeException("Slot already booked");
        }

        slot.setBooked(true);
        slotsRepository.save(slot);
    	
    	Appointments appointments = new Appointments();
    	//Get patient object
    	Patients patients = patientsRepository.findByEmail(appointmentsDto.getPatientId()).orElseThrow();
    	appointments.setPatients(patients);
    	
    	//Get doctor object
    	Doctors doctors = doctorsRepository.findById(appointmentsDto.getDoctorId()).orElseThrow();;
    	appointments.setDoctors(doctors);
    	
    	//Set slot
    	appointments.setSlots(slot);
    	
    	appointments.setAppointmentDate(LocalDate.parse(appointmentsDto.getSelectedDate()));

        appointments.setStatus("CONFIRMED");
        return appointmentsRepository.save(appointments);
    }
}
