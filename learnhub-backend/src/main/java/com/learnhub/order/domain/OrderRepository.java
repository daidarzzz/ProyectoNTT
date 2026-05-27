package com.learnhub.order.domain;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findByUsuarioId(Long usuarioId);
    List<Order> findAll();
    void deleteById(Long id);
}
