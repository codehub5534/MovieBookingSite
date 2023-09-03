package com.moviebooking.theatre.theatreonboard.service;


import com.moviebooking.theatre.theatreonboard.dto.CreateMultipleShowsRequest;
import com.moviebooking.theatre.theatreonboard.dto.CreateShowsRequest;
import com.moviebooking.theatre.theatreonboard.dto.TheatreShowsDTO;
import com.moviebooking.theatre.theatreonboard.dto.UpdatedShowDTO;
import com.moviebooking.theatre.theatreonboard.entity.Show;

import java.time.LocalDate;
import java.util.List;

public interface ShowService {
    List<Show> getAllShows();
    Show getShowById(Long showId);

    public List<Show> createMultipleShows(CreateShowsRequest createShowsRequest);

    public void updateShows(List<UpdatedShowDTO> updatedShows);

    public TheatreShowsDTO getAllShowsForMovieInTheatreAndDate(Long movieId, Long theatreId, LocalDate date) ;

    public void deleteShows(List<Long> showIds);

    public List<TheatreShowsDTO> getAllShowsForCityAndMovie(String city, Long movieId, LocalDate date);

    public void createMultipleShows(CreateMultipleShowsRequest request);
}

