package com.learnhub.user.infrastructure.rest;

import com.learnhub.user.application.UserService;
import com.learnhub.user.application.dto.ActualizarPerfilRequest;
import com.learnhub.user.application.dto.CambiarPasswordRequest;
import com.learnhub.user.application.dto.EstadoRequest;
import com.learnhub.user.application.dto.UserResponse;
import com.learnhub.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<UserResponse> updateEstado(@PathVariable Long id,
                                                      @Valid @RequestBody EstadoRequest request) {
        return ResponseEntity.ok(userService.updateEstado(id, request));
    }
}
