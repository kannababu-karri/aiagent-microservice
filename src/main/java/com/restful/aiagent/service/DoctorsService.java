package com.restful.aiagent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restful.aiagent.entities.Doctors;
import com.restful.aiagent.entities.DoctorsDTO;
import com.restful.aiagent.repository.DoctorsRepository;

@Service
public class DoctorsService {

    private final DoctorsRepository doctorsRepository;
    
    public DoctorsService(DoctorsRepository doctorsRepository) {
    	this.doctorsRepository = doctorsRepository;
    }

    public List<DoctorsDTO> getBySpecialization(Long specializationId) {
        //return doctorsRepository.findBySpecializationId(specId);
    	List<Doctors> doctors = doctorsRepository.findBySpecializationId(specializationId);

        return doctors.stream()
                .map(doc -> new DoctorsDTO(
                        doc.getDoctorId(),
                        doc.getName(),
                        doc.getSpecialization().getName()
                ))
                .toList();
    }
}
