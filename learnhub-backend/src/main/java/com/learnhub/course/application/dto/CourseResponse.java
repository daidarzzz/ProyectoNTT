package com.learnhub.course.application.dto;

import java.math.BigDecimal;

public record CourseResponse(
    Long id,
    String titulo,
    String descripcion,
    String descripcionLarga,
    BigDecimal precio,
    String imagenUrl,
    Long idCategoria,
    Integer horas,
    String autor
) {}
