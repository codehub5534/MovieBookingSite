package com.amit.moviebooking.service.impl;

import com.amit.moviebooking.entity.Seat;
import com.amit.moviebooking.repository.SeatRepository;
import com.amit.moviebooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> getAllSeatsForShow(Long showId) {
        return seatRepository.findByShowId(showId);
    }

    @Override
    public Seat getSeatById(Long seatId) {
        return seatRepository.findById(seatId).orElse(null);
    }

    // Implement other methods for CRUD operations
}

