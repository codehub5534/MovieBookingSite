package com.amit.moviebooking.service;

import com.amit.moviebooking.entity.Booking;
import com.amit.moviebooking.entity.Seat;
import com.amit.moviebooking.entity.Show;
import com.amit.moviebooking.entity.Theatre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingCreationService {

    public Booking createBooking(Show show, Theatre theatre, List<Integer> seatNumbers) {
        // Create the Booking object with show, theatre, seats, and other details
        Booking booking = new Booking();
        booking.setShow(show);
        booking.setTheatre(theatre);
        booking.setSeats(createSeats(show, seatNumbers));
        // Set other details like paymentMethod, totalPayment, etc.
        return booking;
    }

    private List<Seat> createSeats(Show show, List<Integer> seatNumbers) {
        // Create Seat objects for the selected seat numbers
        List<Seat> seats = new ArrayList<>();
        for (Integer seatNumber : seatNumbers) {
            Seat seat = new Seat();
            seat.setShow(show);
            seat.setSeatNumber(String.valueOf(seatNumber));
            seats.add(seat);
        }
        return seats;
    }
}

