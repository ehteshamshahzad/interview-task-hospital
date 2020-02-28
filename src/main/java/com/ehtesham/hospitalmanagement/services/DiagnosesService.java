package com.ehtesham.hospitalmanagement.services;

import com.ehtesham.hospitalmanagement.domain.Diagnoses;
import com.ehtesham.hospitalmanagement.repositories.DiagnosesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosesService {

    @Autowired
    DiagnosesRepository diagnosesRepository;

    public Diagnoses saveOrUpdateDiagnoses(Diagnoses diagnoses) {
        return diagnosesRepository.save(diagnoses);
    }

    public Iterable<Diagnoses> getAllDiagnoses() {
        return diagnosesRepository.findAll();
    }
}
