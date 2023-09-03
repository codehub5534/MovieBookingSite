package com.moviebooking.theatre.theatreonboard.service;

import com.moviebooking.theatre.theatreonboard.entity.Seat;
import com.moviebooking.theatre.theatreonboard.exception.ConcurrencyConflictException;
import com.moviebooking.theatre.theatreonboard.repository.SeatRepository;
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

    public void updateSeatAvailability(Long showId,List<Seat> seats) {

        /*for (Seat seat : seats) {
            Seat existingSeat = seatRepository.findByShowIdAndSeatNumber(showId,seat.getSeatNumber());
            if (existingSeat != null && existingSeat.isAvailable()) {
                existingSeat.setAvailable(false); // Update availability
            } else {
                // Handle concurrency conflict
                // You might retry, notify the user, or implement a custom strategy
                // For now, let's throw an exception
                throw new ConcurrencyConflictException("Seat conflict during booking");
            }
        }*/

        seatRepository.saveAll(seats);
    }

    public void rollbackSeatAvailability(List<Seat> seats) {
        for (Seat seat : seats) {
            seat.setAvailable(true); // Mark the seat as available again
        }
        seatRepository.saveAll(seats);
    }
}

