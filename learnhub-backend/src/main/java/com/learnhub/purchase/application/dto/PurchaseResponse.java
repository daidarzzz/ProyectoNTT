package com.learnhub.purchase.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PurchaseResponse(
    @JsonProperty("id_compra") Long id,
    @JsonProperty("id_usuario") Long idUsuario,
    @JsonProperty("id_curso") Long idCurso,
    @JsonProperty("precio_pagado") BigDecimal precioPagado,
    @JsonProperty("fecha_compra") LocalDateTime fechaCompra,
    @JsonProperty("estado_pago") String estadoPago
) {}
