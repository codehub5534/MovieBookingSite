package com.moviebooking.theatre.theatreonboard.exception;

public class PaymentFailedException extends Throwable {
    public PaymentFailedException(String paymentProcessingFailed) {
        super(paymentProcessingFailed);
    }
}
