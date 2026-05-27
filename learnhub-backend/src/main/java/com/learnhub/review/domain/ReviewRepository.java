package com.learnhub.review.domain;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);
    Optional<Review> findById(Long id);
    List<Review> findByCursoId(Long cursoId);
    List<Review> findByUsuarioId(Long usuarioId);
    Optional<Review> findByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
    boolean existsByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
    void deleteById(Long id);
}
