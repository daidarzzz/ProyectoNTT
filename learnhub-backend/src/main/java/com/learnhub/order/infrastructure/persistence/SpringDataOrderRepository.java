package com.learnhub.order.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT o FROM OrderEntity o WHERE o.idUsuario = ?1 AND o.deleted = false ORDER BY o.fecha DESC")
    List<OrderEntity> findByIdUsuarioActive(Long idUsuario);

    @Query("SELECT o FROM OrderEntity o WHERE o.deleted = false")
    List<OrderEntity> findAllActive();

    @Query("SELECT o FROM OrderEntity o WHERE o.id = ?1 AND o.deleted = false")
    Optional<OrderEntity> findByIdActive(Long id);
}
