package com.learnhub.payment.application.dto;

import java.util.List;

public record CheckoutSessionRequest(
    List<CartItemDto> items,
    String successUrl,
    String cancelUrl
) {
    public record CartItemDto(String name, String description, long unitAmount, int quantity) {}
}
