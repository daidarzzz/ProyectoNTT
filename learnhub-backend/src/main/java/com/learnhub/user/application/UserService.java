package com.learnhub.user.application;

import com.learnhub.shared.domain.exception.ResourceNotFoundException;
import com.learnhub.user.application.dto.UserResponse;
import com.learnhub.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse findById(Long id) {
        var user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        return toResponse(user);
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
