package com.jewellery.backend.controller;

import com.jewellery.backend.entity.User;
import com.jewellery.backend.repository.UserRepository;
import com.jewellery.backend.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // for React
public class AuthController {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if(userRepo.findByUsername(user.getUsername()) != null)
            return ResponseEntity.badRequest().body("User exists");
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole() == null) user.setRole("ROLE_CUSTOMER");
        userRepo.save(user);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        User u = userRepo.findByUsername(user.getUsername());
        String token = jwtUtil.generateToken(u.getUsername(), u.getRole());
        return ResponseEntity.ok(Map.of("token", token, "role", u.getRole()));
    }
}