package com.moviebooking.theatre.theatreonboard.exception;

public class TheatreNotFoundException extends RuntimeException {


    public TheatreNotFoundException(String msg){
        super(msg);
    }
}
