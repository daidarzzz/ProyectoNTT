package com.learnhub.purchase.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Purchase {

    private Long id;
    private Long idUsuario;
    private Long idCurso;
    private BigDecimal precioPagado;
    private LocalDateTime fechaCompra;
    private PaymentStatus estadoPago;

    public Purchase() {}

    public Purchase(Long idUsuario, Long idCurso, BigDecimal precioPagado) {
        this.idUsuario = idUsuario;
        this.idCurso = idCurso;
        this.precioPagado = precioPagado;
        this.fechaCompra = LocalDateTime.now();
        this.estadoPago = PaymentStatus.COMPLETADO;
    }

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
