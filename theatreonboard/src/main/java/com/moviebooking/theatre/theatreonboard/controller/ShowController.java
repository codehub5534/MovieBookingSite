package com.moviebooking.theatre.theatreonboard.controller;

import com.moviebooking.theatre.theatreonboard.dto.CreateMultipleShowsRequest;
import com.moviebooking.theatre.theatreonboard.dto.CreateShowsRequest;
import com.moviebooking.theatre.theatreonboard.dto.TheatreShowsDTO;
import com.moviebooking.theatre.theatreonboard.dto.UpdatedShowDTO;
import com.moviebooking.theatre.theatreonboard.entity.Show;
import com.moviebooking.theatre.theatreonboard.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/theatres/shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/create-multiple")
    public ResponseEntity<String> createShow(@RequestBody CreateShowsRequest request) {
        try {
            showService.createMultipleShows(request);
            return ResponseEntity.ok("Shows created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating shows: " + e.getMessage());
        }
    }

    @PostMapping("/create-multiple-screen")
    public ResponseEntity<String> createShowsWithScreen(@RequestBody CreateMultipleShowsRequest request) {
        try {
            showService.createMultipleShows(request);
            return ResponseEntity.ok("Shows created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating shows: " + e.getMessage());
        }
    }

    @GetMapping("/movie/{movieId}/theatre/{theatreId}")
    public ResponseEntity<TheatreShowsDTO> getAllShowsForMovieInTheatre(
            @PathVariable Long movieId,
            @PathVariable Long theatreId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        TheatreShowsDTO theatreShow = showService.getAllShowsForMovieInTheatreAndDate( movieId,theatreId, date);
        return ResponseEntity.ok(theatreShow);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateShows(@RequestBody List<UpdatedShowDTO> updatedShows) {
        // Call a service method to update the shows based on the provided data
        showService.updateShows(updatedShows);
        return ResponseEntity.ok("Shows updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteShows(@RequestParam List<Long> showIds) {
        // Call a service method to delete the shows based on the provided show IDs
        showService.deleteShows(showIds);
        return ResponseEntity.ok("Shows deleted successfully");
    }


    @GetMapping("/city/{city}/movie/{movieId}/shows")
    public ResponseEntity<List<TheatreShowsDTO>> getAllShowsForCityAndMovie(
            @PathVariable String city,
            @PathVariable Long movieId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<TheatreShowsDTO> theatreShows = showService.getAllShowsForCityAndMovie(city, movieId, date);
        return ResponseEntity.ok(theatreShows);
    }

}

