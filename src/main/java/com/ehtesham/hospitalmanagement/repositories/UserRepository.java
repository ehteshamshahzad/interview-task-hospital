package com.ehtesham.hospitalmanagement.repositories;

import com.ehtesham.hospitalmanagement.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User getById(Long id);

    @Override
    Iterable<User> findAll();

}
