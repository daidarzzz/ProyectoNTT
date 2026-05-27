package com.learnhub.payment.infrastructure.rest;

import com.learnhub.payment.application.PaymentService;
import com.learnhub.payment.application.dto.CheckoutSessionRequest;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/crear-sesion-checkout")
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestBody CheckoutSessionRequest request) {
        try {
            String checkoutUrl = paymentService.createCheckoutSession(request);
            return ResponseEntity.ok(Map.of("url", checkoutUrl));
        } catch (StripeException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error al crear la sesión de pago: " + e.getMessage()));
        }
    }
}
