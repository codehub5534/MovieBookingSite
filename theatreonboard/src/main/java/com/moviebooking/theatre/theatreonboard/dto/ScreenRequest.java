package com.moviebooking.theatre.theatreonboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenRequest {
    private String screenName;
    private Long screenId;
    private List<SeatRequest> seats;
}
