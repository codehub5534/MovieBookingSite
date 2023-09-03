package com.moviebooking.theatre.theatreonboard.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String movieNotFound) {
        super(movieNotFound);
    }
}
