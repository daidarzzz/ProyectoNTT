package com.learnhub.category.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryResponse(
    @JsonProperty("id_categoria") Long id,
    String nombre,
    String descripcion
) {}
