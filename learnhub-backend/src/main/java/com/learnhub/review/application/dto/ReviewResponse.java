package com.learnhub.review.application.dto;

import java.time.LocalDateTime;

public record ReviewResponse(
    Long id,
    Long idUsuario,
    String nombreUsuario,
    Long idCurso,
    Integer puntuacion,
    String comentario,
    LocalDateTime fecha
) {}
