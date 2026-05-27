package com.learnhub.order.application.dto;

import java.math.BigDecimal;

public record OrderDetailResponse(
    Long id,
    Long idCurso,
    String nombreCurso,
    Integer cantidad,
    BigDecimal precioUnitario,
    BigDecimal subtotal
) {}
