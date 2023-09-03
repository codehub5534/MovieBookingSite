package com.moviebooking.theatre.theatreonboard.controller;

import com.moviebooking.theatre.theatreonboard.bulkoperation.BulkBookingRequest;
import com.moviebooking.theatre.theatreonboard.bulkoperation.BulkCancellationRequest;
import com.moviebooking.theatre.theatreonboard.dto.BookingRequest;
import com.moviebooking.theatre.theatreonboard.entity.Booking;
import com.moviebooking.theatre.theatreonboard.entity.Seat;
import com.moviebooking.theatre.theatreonboard.exception.PaymentFailedException;
import com.moviebooking.theatre.theatreonboard.exception.SeatUnavailableException;
import com.moviebooking.theatre.theatreonboard.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingRequest booking) throws SeatUnavailableException, PaymentFailedException, PaymentFailedException {
        Booking createdBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PostMapping("/allocate-seats")
    public ResponseEntity<Void> allocateSeats(@RequestParam Long showId, @RequestBody List<Seat> allocatedSeats) {
        bookingService.allocateSeats(showId, allocatedSeats);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update-seats")
    public ResponseEntity<Void> updateSeats(@RequestParam Long showId, @RequestBody List<Seat> updatedSeats) {
        bookingService.updateSeats(showId, updatedSeats);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Booking>> bulkBookSeats(@RequestBody BulkBookingRequest request) {
        List<Booking> bookings = bookingService.bulkBookSeats(request.getShowId(), request.getSeatIds());
        return new ResponseEntity<>(bookings, HttpStatus.CREATED);
    }

    @PostMapping("/bulk/cancel")
    public ResponseEntity<Void> bulkCancelBookings(@RequestBody BulkCancellationRequest request) {
        bookingService.bulkCancelBookings(request.getBookingIds());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

