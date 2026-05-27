package com.learnhub.user.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record UserResponse(
    Long id,
    String nombre,
    String apellidos,
    String email,
    String rol,
    String estado,
    @JsonProperty("fecha_alta") LocalDate fechaAlta
) {}
