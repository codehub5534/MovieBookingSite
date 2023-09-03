package com.moviebooking.theatre.theatreonboard.service;

import com.moviebooking.theatre.theatreonboard.entity.Movie;

import java.util.List;

public interface MovieService {

    public Movie createMovie(Movie movie);

    public List<Movie> createMovies(List<Movie> movies);
}
