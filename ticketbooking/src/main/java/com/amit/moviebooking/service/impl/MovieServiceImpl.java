package com.amit.moviebooking.service.impl;

import com.amit.moviebooking.dto.ShowTimingDTO;
import com.amit.moviebooking.dto.TheatreWithShowsDTO;
import com.amit.moviebooking.entity.Movie;
import com.amit.moviebooking.entity.Show;
import com.amit.moviebooking.entity.Theatre;
import com.amit.moviebooking.repository.MovieRepository;
import com.amit.moviebooking.repository.ShowRepository;
import com.amit.moviebooking.repository.TheatreRepository;
import com.amit.moviebooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ShowRepository showRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }

    @Override
    public List<TheatreWithShowsDTO> getTheatresWithShowTimings(String city, String movieName, LocalDateTime date) {
        List<TheatreWithShowsDTO> theatresWithShows = new ArrayList<>();

        List<Theatre> theatresInCity = theatreRepository.findByCityIgnoreCase(city);
        for (Theatre theatre : theatresInCity) {
            List<Show> showsForMovieAndDate = showRepository.findByMovieNameAndDate(movieName, date);
            if (!showsForMovieAndDate.isEmpty()) {
                List<ShowTimingDTO> showTimings = new ArrayList<>();
                for (Show show : showsForMovieAndDate) {
                    showTimings.add(new ShowTimingDTO(show.getShowTime()));
                }
                theatresWithShows.add(new TheatreWithShowsDTO(theatre.getName(), showTimings));
            }
        }

        return theatresWithShows;
    }
    // Implement other methods for CRUD operations
}

