package com.learnhub.user.infrastructure.rest;

import com.learnhub.user.application.AuthService;
import com.learnhub.user.application.dto.LoginRequest;
import com.learnhub.user.application.dto.LoginResponse;
import com.learnhub.user.application.dto.RegisterRequest;
import com.learnhub.user.application.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserResponse> profile(@PathVariable Long id) {
        return ResponseEntity.ok(authService.getProfile(id));
    }
}
