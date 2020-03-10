package com.ehtesham.hospitalmanagement.repositories;

import com.ehtesham.hospitalmanagement.domain.Type;
import com.ehtesham.hospitalmanagement.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Override
    Iterable<User> findAll();

    @Override
    Optional<User> findById(Long aLong);

    Iterable<User> findByUserType(Type type);

    User getById(Long id);
}
