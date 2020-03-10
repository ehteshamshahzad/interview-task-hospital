package com.ehtesham.hospitalmanagement.services;

import com.ehtesham.hospitalmanagement.domain.Type;
import com.ehtesham.hospitalmanagement.domain.User;
import com.ehtesham.hospitalmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {
        newUser.setPassword((bCryptPasswordEncoder.encode(newUser.getPassword())));
//        newUser.setConfirmPassword("");
        newUser.setUsername(newUser.getUsername().toLowerCase());

        return userRepository.save(newUser);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User assignPatientToDoctor(User user, String username) {

        User doctor1 = userRepository.findByUsername(username);
        if ((doctor1.getUserType() == Type.DOCTOR) && (user.getUserType() == Type.PATIENT)) {
            List<User> patientList1 = doctor1.getListOfPatients();
            patientList1.add(user);
            doctor1.setListOfPatients(patientList1);

            return userRepository.save(doctor1);
        } else {
            return userRepository.save(user);
            /*
            * Not a proper way to handle else condition. Need to throw proper exception.
            * */
        }
    }

    public Iterable<User> getAllDoctors(Type type) {
        return userRepository.findByUserType(type);
    }

    public User addDiagnoses(User user) {

        return userRepository.save(user);
    }
}
