package com.learnhub.user.application.dto;

import jakarta.validation.constraints.NotBlank;

public record EstadoRequest(
    @NotBlank String estado
) {}
