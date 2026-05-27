package com.learnhub.payment.application;

import com.learnhub.payment.application.dto.CheckoutSessionRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    public String createCheckoutSession(CheckoutSessionRequest request) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        List<SessionCreateParams.LineItem> lineItems = request.items().stream()
            .map(item -> SessionCreateParams.LineItem.builder()
                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                    .setCurrency("usd")
                    .setUnitAmount(item.unitAmount())
                    .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(item.name())
                        .setDescription(item.description())
                        .build())
                    .build())
                .setQuantity((long) item.quantity())
                .build())
            .collect(Collectors.toList());

        SessionCreateParams params = SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .addAllLineItem(lineItems)
            .setSuccessUrl(request.successUrl())
            .setCancelUrl(request.cancelUrl())
            .build();

        Session session = Session.create(params);
        return session.getUrl();
    }
}
