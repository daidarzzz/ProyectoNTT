package com.learnhub.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ActualizarPerfilRequest(
    @NotBlank String nombre,
    @NotBlank String apellidos,
    @NotBlank @Email String email
) {}
