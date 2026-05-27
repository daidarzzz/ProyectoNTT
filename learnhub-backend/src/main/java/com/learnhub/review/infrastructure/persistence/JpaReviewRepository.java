package com.learnhub.review.infrastructure.persistence;

import com.learnhub.review.domain.Review;
import com.learnhub.review.domain.ReviewRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaReviewRepository implements ReviewRepository {

    private final SpringDataReviewRepository springRepo;
    private final ReviewEntityMapper mapper;

    public JpaReviewRepository(SpringDataReviewRepository springRepo, ReviewEntityMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Review save(Review review) {
        var entity = mapper.toEntity(review);
        var saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Review> findByCursoId(Long cursoId) {
        return springRepo.findByIdCurso(cursoId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Review> findByUsuarioId(Long usuarioId) {
        return springRepo.findByIdUsuario(usuarioId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Review> findByUsuarioIdAndCursoId(Long usuarioId, Long cursoId) {
        return springRepo.findByIdUsuarioAndIdCurso(usuarioId, cursoId).map(mapper::toDomain);
    }

    @Override
    public boolean existsByUsuarioIdAndCursoId(Long usuarioId, Long cursoId) {
        return springRepo.existsByIdUsuarioAndIdCurso(usuarioId, cursoId);
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }
}
