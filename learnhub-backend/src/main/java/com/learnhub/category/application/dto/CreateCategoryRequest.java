package com.learnhub.category.application.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
    @NotBlank String nombre,
    String descripcion
) {}
