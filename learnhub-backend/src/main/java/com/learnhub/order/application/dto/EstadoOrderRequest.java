package com.learnhub.order.application.dto;

import jakarta.validation.constraints.NotBlank;

public record EstadoOrderRequest(
    @NotBlank String estado
) {}
