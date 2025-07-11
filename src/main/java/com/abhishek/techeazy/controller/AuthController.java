package com.abhishek.techeazy.controller;

import com.abhishek.techeazy.dto.LoginRequest;
import com.abhishek.techeazy.dto.RegisterRequest;
import com.abhishek.techeazy.entity.Role;
import com.abhishek.techeazy.entity.User;
import com.abhishek.techeazy.entity.Vendor;
import com.abhishek.techeazy.entity.VendorSubscriptionType;
import com.abhishek.techeazy.repo.UserRepository;
import com.abhishek.techeazy.repo.VendorRepo;
import com.abhishek.techeazy.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/auth")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtils;
    @Autowired private VendorRepo vendorRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        Role role = Role.valueOf(request.getRole().name());

        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getRole());
        userRepo.save(user);

        if (role == Role.VENDOR) {
            Vendor vendor = new Vendor();
            vendor.setName(request.getUsername());
            vendor.setSubscriptionType(VendorSubscriptionType.valueOf("NORMAL"));
            vendorRepo.save(vendor);
        }

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtUtils.generateToken((UserDetails) auth.getPrincipal());
        return ResponseEntity.ok("Bearer " + token);
    }
}
