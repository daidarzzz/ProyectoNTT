package com.learnhub.user.application.dto;

import java.time.LocalDate;

public record UserResponse(
    Long id,
    String nombre,
    String apellidos,
    String email,
    String rol,
    String estado,
    LocalDate fechaAlta
) {}
