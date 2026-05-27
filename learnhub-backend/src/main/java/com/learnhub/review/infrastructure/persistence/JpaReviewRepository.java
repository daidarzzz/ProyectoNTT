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
        return springRepo.findByIdActive(id).map(mapper::toDomain);
    }

    @Override
    public List<Review> findByCursoId(Long cursoId) {
        return springRepo.findByIdCursoActive(cursoId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Review> findByUsuarioId(Long usuarioId) {
        return springRepo.findByIdUsuarioActive(usuarioId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Review> findByUsuarioIdAndCursoId(Long usuarioId, Long cursoId) {
        return springRepo.findByIdUsuarioAndIdCursoActive(usuarioId, cursoId).map(mapper::toDomain);
    }

    @Override
    public boolean existsByUsuarioIdAndCursoId(Long usuarioId, Long cursoId) {
        return springRepo.existsByIdUsuarioAndIdCursoActive(usuarioId, cursoId);
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }

    @Override
    public List<Review> findAll() {
        return springRepo.findAllActive().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Review> findAllIncludingDeleted() {
        return springRepo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Review> findByIdIncludingDeleted(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public void hardDeleteById(Long id) {
        springRepo.deleteById(id);
    }

    @Override
    public void softDeleteById(Long id) {
        springRepo.findById(id).ifPresent(entity -> {
            entity.setDeleted(true);
            springRepo.save(entity);
        });
    }

    @Override
    public void restoreById(Long id) {
        springRepo.findById(id).ifPresent(entity -> {
            entity.setDeleted(false);
            springRepo.save(entity);
        });
    }
}
