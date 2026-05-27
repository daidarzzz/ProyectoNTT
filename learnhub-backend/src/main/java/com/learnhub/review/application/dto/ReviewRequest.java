package com.learnhub.review.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReviewRequest(
    @NotNull Long cursoId,
    @NotNull @Min(1) @Max(5) Integer puntuacion,
    String comentario
) {}
