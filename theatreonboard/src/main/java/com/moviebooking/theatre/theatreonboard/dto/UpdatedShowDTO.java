package com.moviebooking.theatre.theatreonboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedShowDTO {
    private Long showId;
    private LocalTime newShowTime;
}

