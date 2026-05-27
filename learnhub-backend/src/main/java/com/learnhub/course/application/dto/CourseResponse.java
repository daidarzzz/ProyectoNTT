package com.learnhub.course.application.dto;

import java.math.BigDecimal;
import java.util.List;

public record CourseResponse(
    Long id,
    String nombre,
    String descripcion,
    String descripcionLarga,
    BigDecimal precio,
    Long idCategoria,
    Integer horas,
    String autor,
    List<String> imagenes
) {}
