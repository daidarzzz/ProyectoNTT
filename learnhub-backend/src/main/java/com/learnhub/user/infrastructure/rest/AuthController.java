package com.learnhub.user.infrastructure.rest;

import com.learnhub.user.application.AuthService;
import com.learnhub.user.application.dto.ActualizarPerfilRequest;
import com.learnhub.user.application.dto.CambiarPasswordRequest;
import com.learnhub.user.application.dto.LoginRequest;
import com.learnhub.user.application.dto.LoginResponse;
import com.learnhub.user.application.dto.RegisterRequest;
import com.learnhub.user.application.dto.UserResponse;
import com.learnhub.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PutMapping("/profile/{id}")
    public ResponseEntity<UserResponse> updateProfile(@AuthenticationPrincipal User user,
                                                       @PathVariable Long id,
                                                       @Valid @RequestBody ActualizarPerfilRequest request) {
        return ResponseEntity.ok(authService.updateProfile(id, request));
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<Void> cambiarPassword(@AuthenticationPrincipal User user,
                                                 @Valid @RequestBody CambiarPasswordRequest request) {
        authService.cambiarPassword(user.getId(), request);
        return ResponseEntity.ok().build();
    }
}
