package com.amit.moviebooking.exception;

public class PaymentFailedException extends Throwable {
    public PaymentFailedException(String paymentProcessingFailed) {
        super(paymentProcessingFailed);
    }
}
