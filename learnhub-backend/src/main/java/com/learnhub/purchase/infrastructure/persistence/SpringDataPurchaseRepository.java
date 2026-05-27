package com.learnhub.purchase.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    @Query("SELECT p FROM PurchaseEntity p WHERE p.idUsuario = ?1 AND p.deleted = false")
    List<PurchaseEntity> findByIdUsuarioActive(Long idUsuario);

    @Query("SELECT p FROM PurchaseEntity p WHERE p.idCurso = ?1 AND p.deleted = false")
    List<PurchaseEntity> findByIdCursoActive(Long idCurso);

    @Query("SELECT p FROM PurchaseEntity p WHERE p.deleted = false")
    List<PurchaseEntity> findAllActive();

    @Query("SELECT p FROM PurchaseEntity p WHERE p.id = ?1 AND p.deleted = false")
    Optional<PurchaseEntity> findByIdActive(Long id);

    @Query("SELECT p FROM PurchaseEntity p ORDER BY p.fechaCompra DESC")
    List<PurchaseEntity> findAllIncludingDeleted();
}
