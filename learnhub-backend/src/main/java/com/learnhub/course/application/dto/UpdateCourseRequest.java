package com.learnhub.course.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record UpdateCourseRequest(
    @NotBlank String nombre,
    @NotBlank String descripcion,
    @NotBlank String descripcionLarga,
    @NotNull @Positive BigDecimal precio,
    @NotNull Long idCategoria,
    @NotNull @Positive Integer horas,
    @NotBlank String autor,
    List<String> imagenes
) {}
