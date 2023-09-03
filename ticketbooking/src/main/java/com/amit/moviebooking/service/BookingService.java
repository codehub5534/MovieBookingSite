package com.amit.moviebooking.service;

import com.amit.moviebooking.dto.BookingRequest;
import com.amit.moviebooking.entity.Booking;
import com.amit.moviebooking.entity.Seat;
import com.amit.moviebooking.exception.PaymentFailedException;
import com.amit.moviebooking.exception.SeatUnavailableException;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequest booking) throws SeatUnavailableException, PaymentFailedException;
    Booking getBookingById(Long bookingId);

    void allocateSeats(Long showId, List<Seat> allocatedSeats);

    void updateSeats(Long showId, List<Seat> updatedSeats);
    List<Booking> bulkBookSeats(Long showId, List<Long> seatIds);

    void bulkCancelBookings(List<Long> bookingIds);
    // Other methods for CRUD operations
}

