package com.ehtesham.hospitalmanagement.web;

import com.ehtesham.hospitalmanagement.domain.Diagnoses;
import com.ehtesham.hospitalmanagement.services.DiagnosesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v0/diagnoses")
public class DiagnosesController {

    @Autowired
    DiagnosesService diagnosesService;

    @PostMapping("")
    public ResponseEntity<?> createNewDiagnoses(@Valid @RequestBody Diagnoses diagnoses, BindingResult bindingResult) {

        Diagnoses diagnoses1 = diagnosesService.saveOrUpdateDiagnoses(diagnoses);

        return new ResponseEntity<Diagnoses>(diagnoses, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<Diagnoses> getAllProjects() {
        return diagnosesService.getAllDiagnoses();
    }
}
