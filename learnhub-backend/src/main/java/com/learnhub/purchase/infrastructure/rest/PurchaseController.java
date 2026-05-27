package com.learnhub.purchase.infrastructure.rest;

import com.learnhub.purchase.application.PurchaseService;
import com.learnhub.purchase.application.dto.PurchaseRequest;
import com.learnhub.purchase.application.dto.PurchaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
