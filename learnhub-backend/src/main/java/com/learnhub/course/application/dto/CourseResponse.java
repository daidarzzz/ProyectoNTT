package com.learnhub.course.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record CourseResponse(
    @JsonProperty("id_curso") Long id,
    String titulo,
    String descripcion,
    @JsonProperty("descripcion_larga") String descripcionLarga,
    BigDecimal precio,
    @JsonProperty("imagen_url") String imagenUrl,
    @JsonProperty("id_categoria") Long idCategoria,
    Integer horas,
    String autor
) {}
