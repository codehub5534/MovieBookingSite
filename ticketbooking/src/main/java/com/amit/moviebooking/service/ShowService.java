package com.amit.moviebooking.service;

import com.amit.moviebooking.entity.Show;

import java.util.List;

public interface ShowService {
    List<Show> getAllShows();
    Show getShowById(Long showId);

    Show createShow(Show show);

    Show updateShow(Long showId, Show updatedShow);

    void deleteShow(Long showId);
    // Other methods for CRUD operations
}

