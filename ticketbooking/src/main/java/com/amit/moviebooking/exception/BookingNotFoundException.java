package com.amit.moviebooking.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String message) {
        super(message);
    }
}

// Create similar exception classes for other scenarios

