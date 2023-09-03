package com.moviebooking.moviesearch.service;

import com.moviebooking.moviesearch.dto.MovieSearchRequest;
import com.moviebooking.moviesearch.dto.MovieSearchResponse;

import java.util.List;

public interface MovieSearchService {

    public MovieSearchResponse getAllShowsOfMovieInCity(MovieSearchRequest movieSearchRequest);

    public List<MovieSearchResponse> getAllMoviesInCity(String city);

}
