package com.amit.moviebooking.service;

import com.amit.moviebooking.dto.TheatreWithShowsDTO;
import com.amit.moviebooking.entity.Movie;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Long movieId);

    public List<TheatreWithShowsDTO> getTheatresWithShowTimings(String city, String movieName, LocalDateTime date);
    // Other methods for CRUD operations
}

