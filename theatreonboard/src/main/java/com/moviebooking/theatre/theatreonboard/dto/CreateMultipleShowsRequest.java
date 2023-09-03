package com.moviebooking.theatre.theatreonboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMultipleShowsRequest {
    private Long theatreId;
    private LocalDate date;
    private List<ShowRequest> showRequests;
}

