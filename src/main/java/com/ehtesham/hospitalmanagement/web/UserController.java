package com.ehtesham.hospitalmanagement.web;

import com.ehtesham.hospitalmanagement.domain.User;
import com.ehtesham.hospitalmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{user_id}")
    public Optional<User> getUserByUserId(@PathVariable Long user_id) {
        return userService.findUserById(user_id);
    }

    @PostMapping("/add_patient")
    public ResponseEntity<?> addPatient(@Valid @RequestBody User user, BindingResult result){
        User doctor = userService.assignPatientToDoctor(user);
        return new ResponseEntity<User>(doctor, HttpStatus.CREATED);
    }
}
