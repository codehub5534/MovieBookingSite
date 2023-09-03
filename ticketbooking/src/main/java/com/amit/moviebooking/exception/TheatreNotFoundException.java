package com.amit.moviebooking.exception;

public class TheatreNotFoundException extends RuntimeException {
    public TheatreNotFoundException(String theatreNotFound) {
        super(theatreNotFound);
    }
}
