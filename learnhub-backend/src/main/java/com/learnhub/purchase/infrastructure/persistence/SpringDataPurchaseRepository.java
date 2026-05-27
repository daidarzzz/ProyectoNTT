package com.learnhub.purchase.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    List<PurchaseEntity> findByIdUsuario(Long idUsuario);
    List<PurchaseEntity> findByIdCurso(Long idCurso);
}
