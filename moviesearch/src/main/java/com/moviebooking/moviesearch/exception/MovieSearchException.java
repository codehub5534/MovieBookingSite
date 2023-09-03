package com.moviebooking.moviesearch.exception;

public class MovieSearchException extends RuntimeException{

   private String msg;
    public MovieSearchException(String msg){
        super(msg);
    }
}
