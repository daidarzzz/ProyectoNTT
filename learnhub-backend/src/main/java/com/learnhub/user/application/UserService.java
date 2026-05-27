package com.learnhub.user.application;

import com.learnhub.shared.domain.exception.DuplicateResourceException;
import com.learnhub.shared.domain.exception.ResourceNotFoundException;
import com.learnhub.user.application.dto.CreateUserRequest;
import com.learnhub.user.application.dto.EstadoRequest;
import com.learnhub.user.application.dto.UpdateUserRequest;
import com.learnhub.user.application.dto.UserResponse;
import com.learnhub.user.domain.User;
import com.learnhub.user.domain.UserRepository;
import com.learnhub.user.domain.UserRole;
import com.learnhub.user.domain.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public List<UserResponse> findAllIncludingDeleted() {
        return userRepository.findAllIncludingDeleted().stream().map(this::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        var user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        return toResponse(user);
    }

    public UserResponse findByIdIncludingDeleted(Long id) {
        var user = userRepository.findByIdIncludingDeleted(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        return toResponse(user);
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("El email " + request.email() + " ya está registrado");
        }

        UserRole rol = UserRole.CLIENTE;
        if (request.rol() != null && !request.rol().isBlank()) {
            rol = UserRole.valueOf(request.rol().toUpperCase());
        }

        var user = new User(
            request.nombre(),
            request.apellidos(),
            request.email(),
            passwordEncoder.encode(request.password()),
            rol
        );

        return toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        var user = userRepository.findByIdIncludingDeleted(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));

        user.setNombre(request.nombre());
        user.setApellidos(request.apellidos());
        user.setEmail(request.email());
        user.setRol(UserRole.valueOf(request.rol().toUpperCase()));

        return toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse updateEstado(Long id, EstadoRequest request) {
        var user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));

        user.setEstado(UserStatus.valueOf(request.estado().toUpperCase()));
        return toResponse(userRepository.save(user));
    }

    @Transactional
    public void softDelete(Long id) {
        if (userRepository.findByIdIncludingDeleted(id).isEmpty()) {
            throw new ResourceNotFoundException("Usuario", id);
        }
        userRepository.softDeleteById(id);
    }

    @Transactional
    public void hardDelete(Long id) {
        if (userRepository.findByIdIncludingDeleted(id).isEmpty()) {
            throw new ResourceNotFoundException("Usuario", id);
        }
        userRepository.hardDeleteById(id);
    }

    @Transactional
    public void restore(Long id) {
        if (userRepository.findByIdIncludingDeleted(id).isEmpty()) {
            throw new ResourceNotFoundException("Usuario", id);
        }
        userRepository.restoreById(id);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getNombre(),
            user.getApellidos(),
            user.getEmail(),
            user.getRol().name(),
            user.getEstado().name(),
            user.getFechaAlta()
        );
    }
}
