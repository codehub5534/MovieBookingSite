package com.moviebooking.theatre.theatreonboard.controller;

import com.moviebooking.theatre.theatreonboard.entity.Movie;
import com.moviebooking.theatre.theatreonboard.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/create-multiple")
    public ResponseEntity<List<Movie>> createMultipleMovies(@RequestBody List<Movie> movies) {
        List<Movie> createdMovies = movieService.createMovies(movies);
        return new ResponseEntity<>(createdMovies, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

}

