package com.learnhub.purchase.infrastructure.persistence;

import com.learnhub.purchase.domain.Purchase;
import com.learnhub.purchase.domain.PurchaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaPurchaseRepository implements PurchaseRepository {

    private final SpringDataPurchaseRepository springRepo;
    private final PurchaseEntityMapper mapper;

    public JpaPurchaseRepository(SpringDataPurchaseRepository springRepo, PurchaseEntityMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Purchase save(Purchase purchase) {
        var entity = mapper.toEntity(purchase);
        var saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Purchase> findById(Long id) {
        return springRepo.findByIdActive(id).map(mapper::toDomain);
    }

    @Override
    public List<Purchase> findByUsuarioId(Long usuarioId) {
        return springRepo.findByIdUsuarioActive(usuarioId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Purchase> findAll() {
        return springRepo.findAllActive().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Purchase> findByCursoId(Long cursoId) {
        return springRepo.findByIdCursoActive(cursoId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Purchase> findAllIncludingDeleted() {
        return springRepo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Purchase> findByIdIncludingDeleted(Long id) {
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
