package com.moviebooking.moviesearch.controller;

import com.moviebooking.moviesearch.dto.MovieSearchRequest;
import com.moviebooking.moviesearch.dto.MovieSearchResponse;
import com.moviebooking.moviesearch.service.MovieSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieSearchController {

    @Autowired
    MovieSearchService movieSearchService;

    @PostMapping
    public MovieSearchResponse getAllShowsOfMovieInCity(@RequestBody MovieSearchRequest movieSearchRequest){
        // All movies with language
      return   movieSearchService.getAllShowsOfMovieInCity(movieSearchRequest);

    }

    @GetMapping("{city}/movies")
    public List<MovieSearchResponse> getAllMoviesOfCity(@PathVariable String city){
        return movieSearchService.getAllMoviesInCity(city);
    }
}
