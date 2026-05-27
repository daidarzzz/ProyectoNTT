package com.learnhub.purchase.infrastructure.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.learnhub.purchase.domain.PaymentStatus;

@Entity
@Table(name = "compras")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @Column(name = "precio_pagado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioPagado;

    @Column(name = "fecha_compra", nullable = false, updatable = false)
    private LocalDateTime fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false)
    private PaymentStatus estadoPago;

    public PurchaseEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Long getIdCurso() { return idCurso; }
    public void setIdCurso(Long idCurso) { this.idCurso = idCurso; }

    public BigDecimal getPrecioPagado() { return precioPagado; }
    public void setPrecioPagado(BigDecimal precioPagado) { this.precioPagado = precioPagado; }

    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }

    public PaymentStatus getEstadoPago() { return estadoPago; }
    public void setEstadoPago(PaymentStatus estadoPago) { this.estadoPago = estadoPago; }
}
