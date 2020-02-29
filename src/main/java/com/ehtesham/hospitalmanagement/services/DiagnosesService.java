package com.ehtesham.hospitalmanagement.services;

import com.ehtesham.hospitalmanagement.domain.Diagnoses;
import com.ehtesham.hospitalmanagement.domain.User;
import com.ehtesham.hospitalmanagement.repositories.DiagnosesRepository;
import com.ehtesham.hospitalmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosesService {

    @Autowired
    DiagnosesRepository diagnosesRepository;

    @Autowired
    UserRepository userRepository;

    public Diagnoses saveOrUpdateDiagnoses(Diagnoses diagnoses) {
        User patient = userRepository.findByUsername(diagnoses.getPatient().getUsername());
        diagnoses.setPatientName(patient.getFullName());
        return diagnosesRepository.save(diagnoses);
    }

    public Iterable<Diagnoses> getAllDiagnoses() {
        return diagnosesRepository.findAll();
    }
}
