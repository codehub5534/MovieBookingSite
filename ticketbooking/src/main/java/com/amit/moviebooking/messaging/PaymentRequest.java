package com.amit.moviebooking.messaging;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
    private String paymentMethod;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private double amount;
    private String correlationId;

    // Getters and setters...
}

