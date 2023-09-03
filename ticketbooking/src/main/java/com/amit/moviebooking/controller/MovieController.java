package com.amit.moviebooking.controller;

import com.amit.moviebooking.dto.TheatreWithShowsDTO;
import com.amit.moviebooking.entity.Movie;
import com.amit.moviebooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/theatres")
    public ResponseEntity<List<TheatreWithShowsDTO>> getTheatresWithShowTimings(
            @RequestParam String city,
            @RequestParam String movieName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<TheatreWithShowsDTO> theatresWithShows = movieService.getTheatresWithShowTimings(city, movieName, date);
        return ResponseEntity.ok(theatresWithShows);
    }

    // Other methods for CRUD operations
}
