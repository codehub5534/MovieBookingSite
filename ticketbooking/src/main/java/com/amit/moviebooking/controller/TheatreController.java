package com.amit.moviebooking.controller;

import com.amit.moviebooking.entity.Theatre;
import com.amit.moviebooking.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @GetMapping
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    @GetMapping("/{theatreId}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable Long theatreId) {
        Theatre theatre = theatreService.getTheatreById(theatreId);
        if (theatre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(theatre, HttpStatus.OK);
    }

    // Other methods for CRUD operations
}

