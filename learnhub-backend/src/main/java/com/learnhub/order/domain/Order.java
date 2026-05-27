package com.learnhub.order.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long id;
    private Long idUsuario;
    private LocalDateTime fecha;
    private BigDecimal total;
    private OrderStatus estado;
    private List<OrderDetail> detalles;

    public Order() {
        this.detalles = new ArrayList<>();
    }

    public Order(Long idUsuario) {
        this.idUsuario = idUsuario;
        this.fecha = LocalDateTime.now();
        this.estado = OrderStatus.PENDIENTE;
        this.total = BigDecimal.ZERO;
        this.detalles = new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public OrderStatus getEstado() { return estado; }
    public void setEstado(OrderStatus estado) { this.estado = estado; }

    public List<OrderDetail> getDetalles() { return detalles; }
    public void setDetalles(List<OrderDetail> detalles) { this.detalles = detalles; }

    public void recalcularTotal() {
        this.total = detalles.stream()
            .map(d -> d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
