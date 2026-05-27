package com.learnhub.review.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByIdCurso(Long cursoId);
    List<ReviewEntity> findByIdUsuario(Long usuarioId);
    Optional<ReviewEntity> findByIdUsuarioAndIdCurso(Long usuarioId, Long cursoId);
    boolean existsByIdUsuarioAndIdCurso(Long usuarioId, Long cursoId);
}
