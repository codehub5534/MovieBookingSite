package com.amit.moviebooking.controller;

import com.amit.moviebooking.entity.Seat;
import com.amit.moviebooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/show/{showId}")
    public List<Seat> getAllSeatsForShow(@PathVariable Long showId) {
        return seatService.getAllSeatsForShow(showId);
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long seatId) {
        Seat seat = seatService.getSeatById(seatId);
        if (seat == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    // Other methods for CRUD operations
}

