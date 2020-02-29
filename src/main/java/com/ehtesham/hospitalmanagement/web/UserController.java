package com.ehtesham.hospitalmanagement.web;

import com.ehtesham.hospitalmanagement.domain.User;
import com.ehtesham.hospitalmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user_id/{user_id}")
    public ResponseEntity<?> getUserByUserId(@PathVariable Long user_id) {
        Optional<User> user = userService.findUserById(user_id);
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }

    @GetMapping("/user_name/{username}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/add_patient")
    public ResponseEntity<?> addPatient(@Valid @RequestBody User user, BindingResult result) {
        User doctor = userService.assignPatientToDoctor(user);
        return new ResponseEntity<User>(doctor, HttpStatus.CREATED);
    }

    @GetMapping("/patients_list/{username}")
    public List<User> getListOfPatients(@PathVariable String username) {
        return userService.findUserByUsername(username).getListOfPatients();
    }
}
