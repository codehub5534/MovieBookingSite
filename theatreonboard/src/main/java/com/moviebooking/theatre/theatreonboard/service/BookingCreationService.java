package com.moviebooking.theatre.theatreonboard.service;

import com.moviebooking.theatre.theatreonboard.entity.Booking;
import com.moviebooking.theatre.theatreonboard.entity.Seat;
import com.moviebooking.theatre.theatreonboard.entity.Show;
import com.moviebooking.theatre.theatreonboard.entity.Theatre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingCreationService {

    public Booking createBooking(Show show, Theatre theatre, List<String> seatNumbers) {
        // Create the Booking object with show, theatre, seats, and other details
        Booking booking = new Booking();
        booking.setShow(show);
        booking.setSeats(createSeats(show, seatNumbers));
        // Set other details like paymentMethod, totalPayment, etc.
        return booking;
    }

    private List<Seat> createSeats(Show show, List<String> seatNumbers) {
        // Create Seat objects for the selected seat numbers
        List<Seat> seats = new ArrayList<>();
        for (String seatNumber : seatNumbers) {
            Seat seat = new Seat();
            seat.setShow(show);
            seat.setSeatNumber(seatNumber);
            seats.add(seat);
        }
        return seats;
    }
}

