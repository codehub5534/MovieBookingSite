package com.moviebooking.theatre.theatreonboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenWithSeatsDTO {
    private Long screenId;
    private String screenName;
    private List<SeatDTO> seats;
}