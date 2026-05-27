package com.learnhub.purchase.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PurchaseRequest(
    @JsonProperty("id_usuario") @NotNull Long idUsuario,
    @JsonProperty("id_curso") @NotNull Long idCurso,
    @JsonProperty("precio_pagado") @NotNull @Positive BigDecimal precioPagado
) {}
