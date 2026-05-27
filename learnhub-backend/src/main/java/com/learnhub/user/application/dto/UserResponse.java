package com.learnhub.user.application.dto;

import java.time.LocalDateTime;

public record UserResponse(
    Long id,
    String nombre,
    String apellidos,
    String email,
    String rol,
    String estado,
    LocalDateTime fechaAlta
) {}
