package com.TodoProj.TodoProj.controller;

import com.TodoProj.TodoProj.Repository.UserRepository;
import com.TodoProj.TodoProj.models.User;
import com.TodoProj.TodoProj.service.UserService;
import com.TodoProj.TodoProj.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

//    @Autowired i used requiredArgsConstructor so no need for this

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String>body){
                String email= body.get("email");
                String password= passwordEncoder.encode(body.get("password"));

        if (userRepository.findByEmail(email).isPresent()) {
            return new ResponseEntity<>("email already exists",HttpStatus.CONFLICT);
        }
        else{
            userService.createUser(User.builder().email(email).password(password).build());
            return new ResponseEntity<>("successfully registered",HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String>body){
        String email= body.get("email");
        String password= body.get("password");

        var userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Account does not exist. Please register."));
        }


        User user=userOptional.get();

        if (!passwordEncoder.matches(password,user.getPassword())){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
        }


        String token = jwtUtils.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));
    }


}
