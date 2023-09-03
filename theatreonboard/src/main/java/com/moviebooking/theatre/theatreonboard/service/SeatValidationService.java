package com.moviebooking.theatre.theatreonboard.service;

import com.moviebooking.theatre.theatreonboard.entity.Seat;
import com.moviebooking.theatre.theatreonboard.entity.Show;
import com.moviebooking.theatre.theatreonboard.exception.SeatUnavailableException;
import com.moviebooking.theatre.theatreonboard.repository.SeatRepository;
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

    public void validateSeatAvailability(Show show, List<String> seatNumbers) throws SeatUnavailableException {
        List<Seat> bookedSeats = seatRepository.findByShowAndSeatNumberIn(show, seatNumbers);

        if (bookedSeats.size() > 0) {
            throw new SeatUnavailableException("One or more selected seats are already booked");
        }
    }
}

