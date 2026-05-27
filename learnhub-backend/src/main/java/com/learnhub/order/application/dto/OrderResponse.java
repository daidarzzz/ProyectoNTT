package com.learnhub.order.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
    Long id,
    Long idUsuario,
    LocalDateTime fecha,
    BigDecimal total,
    String estado,
    List<OrderDetailResponse> detalles
) {}
