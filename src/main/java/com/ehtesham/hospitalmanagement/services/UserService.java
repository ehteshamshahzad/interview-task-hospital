package com.ehtesham.hospitalmanagement.services;

import com.ehtesham.hospitalmanagement.domain.User;
import com.ehtesham.hospitalmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User newUser) {

        newUser.setUsername(newUser.getUsername().toLowerCase());
        return userRepository.save(newUser);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
