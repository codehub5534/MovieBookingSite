package com.amit.moviebooking.service;

import com.amit.moviebooking.entity.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> getAllSeatsForShow(Long showId);
    Seat getSeatById(Long seatId);
    // Other methods for CRUD operations
}

