package com.ehtesham.hospitalmanagement.web;

import com.ehtesham.hospitalmanagement.domain.Type;
import com.ehtesham.hospitalmanagement.domain.User;
import com.ehtesham.hospitalmanagement.payload.JWTLoginSuccessResponse;
import com.ehtesham.hospitalmanagement.payload.LoginRequest;
import com.ehtesham.hospitalmanagement.security.JwtTokenProvider;
import com.ehtesham.hospitalmanagement.services.UserService;
import com.ehtesham.hospitalmanagement.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static com.ehtesham.hospitalmanagement.services.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/v0/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        userValidator.validate(user, result);
        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
//        ResponseEntity<?>
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
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
    public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/add_patient")
    public ResponseEntity<?> addPatient(@Valid @RequestBody User user, BindingResult result, Principal principal) {
        User doctor = userService.assignPatientToDoctor(user);
        return new ResponseEntity<User>(doctor, HttpStatus.CREATED);
    }

    @GetMapping("/patients_list/")
    public List<User> getListOfPatients(@PathVariable String username, Principal principal) {
        return userService.findUserByUsername(username).getListOfPatients();
    }

    @GetMapping("/doctors_list")
    public Iterable<User> getListOfDoctors() {
        return userService.getAllDoctors(Type.DOCTOR);
    }
}
