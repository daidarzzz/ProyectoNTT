package com.learnhub.purchase.infrastructure.rest;

import com.learnhub.purchase.application.PurchaseService;
import com.learnhub.purchase.application.dto.PurchaseRequest;
import com.learnhub.purchase.application.dto.PurchaseResponse;
import com.learnhub.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<PurchaseResponse> create(@Valid @RequestBody PurchaseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.findById(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PurchaseResponse>> findByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(purchaseService.findByUsuario(usuarioId));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponse>> findAll(
            @RequestParam(defaultValue = "false") boolean incluirEliminados) {
        if (incluirEliminados) {
            return ResponseEntity.ok(purchaseService.findAllIncludingDeleted());
        }
        return ResponseEntity.ok(purchaseService.findAll());
    }

    @PutMapping("/{id}/soft-delete")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        purchaseService.softDelete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/hard")
    public ResponseEntity<Void> hardDelete(@PathVariable Long id) {
        purchaseService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        purchaseService.restore(id);
        return ResponseEntity.ok().build();
    }
}
