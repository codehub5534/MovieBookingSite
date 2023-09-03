package com.moviebooking.theatre.theatreonboard.service;

import com.moviebooking.theatre.theatreonboard.dto.CreateTheatreRequest;
import com.moviebooking.theatre.theatreonboard.dto.TheatreWithScreensAndSeatsDTO;
import com.moviebooking.theatre.theatreonboard.entity.Theatre;
import com.moviebooking.theatre.theatreonboard.exception.TheatreNotFoundException;

import java.util.List;

public interface TheatreService {
    public Theatre createTheatre(Theatre theatre);

    public TheatreWithScreensAndSeatsDTO findTheatreById(Long id) throws TheatreNotFoundException;


    public void createTheatreWithScreensAndSeats(CreateTheatreRequest request);

    public List<TheatreWithScreensAndSeatsDTO> getAllTheatresInCityWithScreensAndSeats(String city);
}
