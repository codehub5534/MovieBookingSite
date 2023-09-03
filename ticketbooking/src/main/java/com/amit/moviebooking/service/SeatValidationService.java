package com.amit.moviebooking.service;

import com.amit.moviebooking.entity.Seat;
import com.amit.moviebooking.entity.Show;
import com.amit.moviebooking.exception.SeatUnavailableException;
import com.amit.moviebooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatValidationService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatValidationService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void validateSeatAvailability(Show show, List<Integer> seatNumbers) throws SeatUnavailableException {
        List<Seat> bookedSeats = seatRepository.findByShowAndSeatNumberIn(show, seatNumbers);

        if (bookedSeats.size() > 0) {
            throw new SeatUnavailableException("One or more selected seats are already booked");
        }
    }
}

