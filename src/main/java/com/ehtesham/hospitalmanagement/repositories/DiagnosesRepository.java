package com.ehtesham.hospitalmanagement.repositories;

import com.ehtesham.hospitalmanagement.domain.Diagnoses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosesRepository extends CrudRepository<Diagnoses, Long> {

    @Override
    Iterable<Diagnoses> findAll();
}
