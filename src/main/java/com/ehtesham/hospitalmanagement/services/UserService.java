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

    public User getUserById(Long id){
        return userRepository.getById(id);
    }

    public User assignPatientToDoctor(User user) {
        /*
         * Problems:
         *   1. Need to handle exception when User any user Type tries to add any other user Type into its list of Patients.
         *   2. Need to make sure the user being added into list of patients actually exists.
         *
         * Note: The proper way is to send User type DOCTOR's ID (or authentication string) and the User type PATIENT's object
         * */

        if (user.getUserType() == Type.DOCTOR) {
            User doctor = userRepository.findByUsername(user.getUsername());
            List<User> patientList = new ArrayList<>();
            patientList = doctor.getListOfPatients();

            patientList.add(user.getListOfPatients().get(user.getListOfPatients().size() - 1));
            doctor.setListOfPatients(patientList);

            return userRepository.save(doctor);
        } else {
            return userRepository.save(user);
        }
    }

    public Iterable<User> getAllDoctors(Type type) {
        return userRepository.findByUserType(type);
    }

    public User addDiagnoses(User user) {

        return userRepository.save(user);
    }
}
