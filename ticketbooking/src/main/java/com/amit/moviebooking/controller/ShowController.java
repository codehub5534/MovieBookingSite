package com.amit.moviebooking.controller;

import com.amit.moviebooking.entity.Show;
import com.amit.moviebooking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }

    @GetMapping("/{showId}")
    public ResponseEntity<Show> getShowById(@PathVariable Long showId) {
        Show show = showService.getShowById(showId);
        if (show == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(show, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        Show createdShow = showService.createShow(show);
        return new ResponseEntity<>(createdShow, HttpStatus.CREATED);
    }

    @PutMapping("/{showId}")
    public ResponseEntity<Show> updateShow(@PathVariable Long showId, @RequestBody Show updatedShow) {
        Show updated = showService.updateShow(showId, updatedShow);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long showId) {
        showService.deleteShow(showId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Other methods for CRUD operations
}

