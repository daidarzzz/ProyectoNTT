package com.learnhub.user.application;

import com.learnhub.shared.domain.exception.ResourceNotFoundException;
import com.learnhub.user.application.dto.ActualizarPerfilRequest;
import com.learnhub.user.application.dto.CambiarPasswordRequest;
import com.learnhub.user.application.dto.EstadoRequest;
import com.learnhub.user.application.dto.UserResponse;
import com.learnhub.user.domain.UserRepository;
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
    public UserResponse updateProfile(Long id, ActualizarPerfilRequest request) {
        var user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));

        user.setNombre(request.nombre());
        user.setApellidos(request.apellidos());
        user.setEmail(request.email());

        return toResponse(userRepository.save(user));
    }

    @Transactional
    public void cambiarPassword(Long id, CambiarPasswordRequest request) {
        var user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));

        if (!passwordEncoder.matches(request.passwordActual(), user.getPassword())) {
            throw new IllegalArgumentException("La contraseña actual no es correcta");
        }

        user.setPassword(passwordEncoder.encode(request.nuevaPassword()));
        userRepository.save(user);
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

    private UserResponse toResponse(com.learnhub.user.domain.User user) {
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
