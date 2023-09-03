package com.amit.moviebooking.entity;


public enum PaymentStatus {
    PENDING("Pending"),
    SUCCESSFUL("Successful"),
    FAILED("Failed"),
    PAID("Paid");

    private String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // You can also add other methods or properties if needed
}

