package com.learnhub.course.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateCourseRequest(
    @NotBlank String titulo,
    @NotBlank String descripcion,
    @NotBlank String descripcionLarga,
    @NotNull @Positive BigDecimal precio,
    String imagenUrl,
    @NotNull Long idCategoria,
    @NotNull @Positive Integer horas,
    @NotBlank String autor
) {}
