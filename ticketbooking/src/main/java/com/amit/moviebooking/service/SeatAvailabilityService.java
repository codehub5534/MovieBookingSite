package com.amit.moviebooking.service;

import com.amit.moviebooking.entity.Seat;
import com.amit.moviebooking.exception.ConcurrencyConflictException;
import com.amit.moviebooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatAvailabilityService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatAvailabilityService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void updateSeatAvailability(List<Seat> seats) {

        for (Seat seat : seats) {
            Seat existingSeat = seatRepository.findById(seat.getId()).orElse(null);
            if (existingSeat != null && existingSeat.isAvailable()) {
                existingSeat.setAvailable(false); // Update availability
            } else {
                // Handle concurrency conflict
                // You might retry, notify the user, or implement a custom strategy
                // For now, let's throw an exception
                throw new ConcurrencyConflictException("Seat conflict during booking");
            }
        }

        seatRepository.saveAll(seats);
    }

    public void rollbackSeatAvailability(List<Seat> seats) {
        for (Seat seat : seats) {
            seat.setAvailable(true); // Mark the seat as available again
        }
        seatRepository.saveAll(seats);
    }
}

