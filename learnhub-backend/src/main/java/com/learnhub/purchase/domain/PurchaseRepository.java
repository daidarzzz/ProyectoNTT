package com.learnhub.purchase.domain;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    Purchase save(Purchase purchase);
    Optional<Purchase> findById(Long id);
    List<Purchase> findByUsuarioId(Long usuarioId);
    List<Purchase> findByCursoId(Long cursoId);
}
