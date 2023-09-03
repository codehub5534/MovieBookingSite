package com.moviebooking.moviesearch.service;

import com.moviebooking.moviesearch.dto.MovieSearchRequest;
import com.moviebooking.moviesearch.dto.MovieSearchResponse;
import com.moviebooking.moviesearch.entity.Movie;
import com.moviebooking.moviesearch.entity.Show;
import com.moviebooking.moviesearch.entity.Theatre;
import com.moviebooking.moviesearch.helper.MovieSearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovieSearchServiceImpl implements MovieSearchService{

    @Autowired
    MovieSearchHelper movieSearchHelper;

    @Override
    public MovieSearchResponse getAllShowsOfMovieInCity(MovieSearchRequest movieSearchRequest) {
        List<Show> shows =movieSearchHelper.getAllShowsOfMovie(movieSearchRequest);
        MovieSearchResponse movieSearchResponse=
                 MovieSearchResponse.builder().movieName("Gadar2").
        city("patna").language("hindi").shows(shows).build();
        return movieSearchResponse;
    }



    @Override
    public List<MovieSearchResponse> getAllMoviesInCity(String city) {
        // get All Theatres
        // get All Movies in Theatres
        List<Theatre> theatreList = movieSearchHelper.getAllTheatres(city);
        List<Movie> movieList = movieSearchHelper.getMoviesOfAllTheatresInCity(theatreList);
        return null;
    }
}
