package com.learnhub.review.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query("SELECT r FROM ReviewEntity r WHERE r.idCurso = ?1 AND r.deleted = false")
    List<ReviewEntity> findByIdCursoActive(Long cursoId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.idUsuario = ?1 AND r.deleted = false")
    List<ReviewEntity> findByIdUsuarioActive(Long usuarioId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.idUsuario = ?1 AND r.idCurso = ?2 AND r.deleted = false")
    Optional<ReviewEntity> findByIdUsuarioAndIdCursoActive(Long usuarioId, Long cursoId);

    @Query("SELECT COUNT(r) > 0 FROM ReviewEntity r WHERE r.idUsuario = ?1 AND r.idCurso = ?2 AND r.deleted = false")
    boolean existsByIdUsuarioAndIdCursoActive(Long usuarioId, Long cursoId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.deleted = false")
    List<ReviewEntity> findAllActive();

    @Query("SELECT r FROM ReviewEntity r WHERE r.id = ?1 AND r.deleted = false")
    Optional<ReviewEntity> findByIdActive(Long id);

    @Query("SELECT r FROM ReviewEntity r ORDER BY r.fecha DESC")
    List<ReviewEntity> findAllIncludingDeleted();
}
