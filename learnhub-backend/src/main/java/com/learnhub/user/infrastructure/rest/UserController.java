package com.learnhub.user.infrastructure.rest;

import com.learnhub.user.application.UserService;
import com.learnhub.user.application.dto.CreateUserRequest;
import com.learnhub.user.application.dto.EstadoRequest;
import com.learnhub.user.application.dto.UpdateUserRequest;
import com.learnhub.user.application.dto.UserResponse;
import com.learnhub.user.domain.User;
import com.learnhub.user.domain.UserRole;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<UserResponse>> findAll(
            @RequestParam(defaultValue = "false") boolean incluirEliminados) {
        if (incluirEliminados) {
            return ResponseEntity.ok(userService.findAllIncludingDeleted());
        }
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id,
                                                  @AuthenticationPrincipal User user) {
        if (user.getRol() == UserRole.ADMIN) {
            return ResponseEntity.ok(userService.findByIdIncludingDeleted(id));
        }
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<UserResponse> updateEstado(@PathVariable Long id,
                                                      @Valid @RequestBody EstadoRequest request) {
        return ResponseEntity.ok(userService.updateEstado(id, request));
    }

    @PutMapping("/{id}/soft-delete")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        userService.softDelete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> hardDelete(@PathVariable Long id) {
        userService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        userService.restore(id);
        return ResponseEntity.ok().build();
    }
}
