package com.moviebooking.theatre.theatreonboard.service;


import com.moviebooking.theatre.theatreonboard.dto.BookingRequest;
import com.moviebooking.theatre.theatreonboard.entity.Booking;
import com.moviebooking.theatre.theatreonboard.entity.Seat;
import com.moviebooking.theatre.theatreonboard.exception.PaymentFailedException;
import com.moviebooking.theatre.theatreonboard.exception.SeatUnavailableException;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequest booking) throws SeatUnavailableException, PaymentFailedException, PaymentFailedException, SeatUnavailableException;
    Booking getBookingById(Long bookingId);

    void allocateSeats(Long showId, List<Seat> allocatedSeats);

    void updateSeats(Long showId, List<Seat> updatedSeats);
    List<Booking> bulkBookSeats(Long showId, List<Long> seatIds);

    void bulkCancelBookings(List<Long> bookingIds);
    // Other methods for CRUD operations
}

