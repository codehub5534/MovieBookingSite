package com.moviebooking.moviesearch.helper;

import com.moviebooking.moviesearch.dto.MovieSearchRequest;
import com.moviebooking.moviesearch.entity.Movie;
import com.moviebooking.moviesearch.entity.Show;
import com.moviebooking.moviesearch.entity.Theatre;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSearchHelper {

    public List<Show> getAllShowsOfMovie(MovieSearchRequest movieSearchRequest) {
        List<Show> shows = new ArrayList<>();
       Show show= Show.builder().startTime(LocalTime.of(12,10,05))
                .endTime(LocalTime.of(14,10,15))
                .theaterName("Mona")
                .startDate(LocalDate.of(23,10,10)).build();
        shows.add(show);
        return shows;
    }

    public List<Theatre> getAllTheatres(String city){

        return null;
    }

    public List<Movie> getMoviesOfAllTheatresInCity(List<Theatre> theatreList){
        List<Movie> movieList = new ArrayList<>();

        return movieList;
    }
}
