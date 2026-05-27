package com.learnhub.purchase.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PurchaseResponse(
    Long id,
    Long idUsuario,
    Long idCurso,
    BigDecimal precioPagado,
    LocalDateTime fechaCompra,
    String estadoPago
) {}
