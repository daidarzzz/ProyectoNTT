package com.learnhub.purchase.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PurchaseRequest(
    @NotNull Long idUsuario,
    @NotNull Long idCurso,
    @NotNull @Positive BigDecimal precioPagado
) {}
