package com.learnhub.order.domain;

import java.math.BigDecimal;

public class OrderDetail {

    private Long id;
    private Long idPedido;
    private Long idCurso;
    private Integer cantidad;
    private BigDecimal precioUnitario;

    public OrderDetail() {}

    public OrderDetail(Long idCurso, Integer cantidad, BigDecimal precioUnitario) {
        this.idCurso = idCurso;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public Long getIdCurso() { return idCurso; }
    public void setIdCurso(Long idCurso) { this.idCurso = idCurso; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
}
