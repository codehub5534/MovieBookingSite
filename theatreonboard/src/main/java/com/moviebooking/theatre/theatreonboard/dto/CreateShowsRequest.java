package com.moviebooking.theatre.theatreonboard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CreateShowsRequest {
    private Long movieId;
    private Long theatreId;
    private LocalDate date;

    private Long screenId;
    private List<LocalTime> showTimes;

    // Constructors, getters, and setters

    public CreateShowsRequest() {
        // Default constructor
    }

    public CreateShowsRequest(Long movieId, Long theatreId, LocalDate date, List<LocalTime> showTimes) {
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.date = date;
        this.showTimes = showTimes;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<LocalTime> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(List<LocalTime> showTimes) {
        this.showTimes = showTimes;
    }
}

