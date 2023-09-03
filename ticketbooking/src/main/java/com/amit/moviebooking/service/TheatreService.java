package com.amit.moviebooking.service;

import com.amit.moviebooking.entity.Theatre;

import java.util.List;


public interface TheatreService {
    List<Theatre> getAllTheatres();
    Theatre getTheatreById(Long theatreId);
    // Other methods for CRUD operations
}
