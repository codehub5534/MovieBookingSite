package com.moviebooking.theatre.theatreonboard.messaging;

public class PaymentResponse {
    private String correlationId;
    private boolean success;

    public PaymentResponse(String correlationId, boolean success) {
        this.correlationId = correlationId;
        this.success = success;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public boolean isSuccess() {
        return success;
    }
}

