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
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Purchase> findByUsuarioId(Long usuarioId) {
        return springRepo.findByIdUsuario(usuarioId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Purchase> findByCursoId(Long cursoId) {
        return springRepo.findByIdCurso(cursoId).stream().map(mapper::toDomain).toList();
    }
}
