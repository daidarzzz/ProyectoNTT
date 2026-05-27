package com.learnhub.order.infrastructure.rest;

import com.learnhub.order.application.OrderService;
import com.learnhub.order.application.dto.EstadoOrderRequest;
import com.learnhub.order.application.dto.OrderRequest;
import com.learnhub.order.application.dto.OrderResponse;
import com.learnhub.user.domain.User;
import com.learnhub.user.domain.UserRole;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@AuthenticationPrincipal User user,
                                                 @Valid @RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(orderService.create(user.getId(), request));
    }

    @GetMapping("/mis-pedidos")
    public ResponseEntity<List<OrderResponse>> misPedidos(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.findByUsuario(user.getId()));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@AuthenticationPrincipal User user,
                                                   @PathVariable Long id) {
        boolean isAdmin = user.getRol() == UserRole.ADMIN;
        return ResponseEntity.ok(orderService.findById(id, user.getId(), isAdmin));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<OrderResponse> updateEstado(@AuthenticationPrincipal User user,
                                                       @PathVariable Long id,
                                                       @Valid @RequestBody EstadoOrderRequest request) {
        boolean isAdmin = user.getRol() == UserRole.ADMIN;
        return ResponseEntity.ok(orderService.updateEstado(id, request, isAdmin, user.getId()));
    }
}
