package com.learnhub.user.application;

import com.learnhub.config.JwtUtil;
import com.learnhub.shared.domain.exception.DuplicateResourceException;
import com.learnhub.user.application.dto.ActualizarPerfilRequest;
import com.learnhub.user.application.dto.CambiarPasswordRequest;
import com.learnhub.user.application.dto.LoginRequest;
import com.learnhub.user.application.dto.LoginResponse;
import com.learnhub.user.application.dto.RegisterRequest;
import com.learnhub.user.application.dto.UserResponse;
import com.learnhub.user.domain.User;
import com.learnhub.user.domain.UserRepository;
import com.learnhub.user.domain.UserRole;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {
        var user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new BadCredentialsException("Email o contraseña incorrectos"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Email o contraseña incorrectos");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRol().name());
        return new LoginResponse(token, toResponse(user));
    }

    public LoginResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("El email " + request.email() + " ya está registrado");
        }

        var rol = "admin@gmail.com".equalsIgnoreCase(request.email())
            ? UserRole.ADMIN
            : UserRole.CLIENTE;

        var user = new User(
            request.nombre(),
            request.apellidos(),
            request.email(),
            passwordEncoder.encode(request.password()),
            rol
        );

        user = userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail(), user.getRol().name());
        return new LoginResponse(token, toResponse(user));
    }

    public UserResponse getProfile(Long id) {
        var user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toResponse(user);
    }

    public UserResponse updateProfile(Long id, ActualizarPerfilRequest request) {
        var user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setNombre(request.nombre());
        user.setApellidos(request.apellidos());
        user.setEmail(request.email());

        return toResponse(userRepository.save(user));
    }

    public void cambiarPassword(Long id, CambiarPasswordRequest request) {
        var user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.passwordActual(), user.getPassword())) {
            throw new IllegalArgumentException("La contraseña actual no es correcta");
        }

        user.setPassword(passwordEncoder.encode(request.nuevaPassword()));
        userRepository.save(user);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getNombre(),
            user.getApellidos(),
            user.getEmail(),
            user.getRol().name(),
            user.getEstado().name(),
            user.getFechaAlta().atStartOfDay()
        );
    }
}
