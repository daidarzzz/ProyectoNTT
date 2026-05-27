package com.learnhub.purchase.application;

import com.learnhub.purchase.application.dto.PurchaseRequest;
import com.learnhub.purchase.application.dto.PurchaseResponse;
import com.learnhub.purchase.domain.Purchase;
import com.learnhub.purchase.domain.PurchaseRepository;
import com.learnhub.shared.domain.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseResponse create(PurchaseRequest request) {
        var purchase = new Purchase(
            request.idUsuario(),
            request.idCurso(),
            request.precioPagado()
        );
        return toResponse(purchaseRepository.save(purchase));
    }

    @Transactional(readOnly = true)
    public PurchaseResponse findById(Long id) {
        var purchase = purchaseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Compra", id));
        return toResponse(purchase);
    }

    @Transactional(readOnly = true)
    public List<PurchaseResponse> findByUsuario(Long usuarioId) {
        return purchaseRepository.findByUsuarioId(usuarioId).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<PurchaseResponse> findAll() {
        return purchaseRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<PurchaseResponse> findAllIncludingDeleted() {
        return purchaseRepository.findAllIncludingDeleted().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PurchaseResponse findByIdIncludingDeleted(Long id) {
        var purchase = purchaseRepository.findByIdIncludingDeleted(id)
            .orElseThrow(() -> new ResourceNotFoundException("Compra", id));
        return toResponse(purchase);
    }

    public void softDelete(Long id) {
        if (purchaseRepository.findByIdIncludingDeleted(id).isEmpty()) {
            throw new ResourceNotFoundException("Compra", id);
        }
        purchaseRepository.softDeleteById(id);
    }

    public void hardDelete(Long id) {
        if (purchaseRepository.findByIdIncludingDeleted(id).isEmpty()) {
            throw new ResourceNotFoundException("Compra", id);
        }
        purchaseRepository.hardDeleteById(id);
    }

    public void restore(Long id) {
        if (purchaseRepository.findByIdIncludingDeleted(id).isEmpty()) {
            throw new ResourceNotFoundException("Compra", id);
        }
        purchaseRepository.restoreById(id);
    }

    private PurchaseResponse toResponse(Purchase purchase) {
        return new PurchaseResponse(
            purchase.getId(),
            purchase.getIdUsuario(),
            purchase.getIdCurso(),
            purchase.getPrecioPagado(),
            purchase.getFechaCompra(),
            purchase.getEstadoPago().name()
        );
    }
}
