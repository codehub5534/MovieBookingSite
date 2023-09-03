package com.moviebooking.theatre.theatreonboard.service.impl;

import com.moviebooking.theatre.theatreonboard.entity.Movie;
import com.moviebooking.theatre.theatreonboard.repository.MovieRepository;
import com.moviebooking.theatre.theatreonboard.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> createMovies(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }
}
