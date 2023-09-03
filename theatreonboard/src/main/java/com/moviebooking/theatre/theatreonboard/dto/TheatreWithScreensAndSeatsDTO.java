package com.moviebooking.theatre.theatreonboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheatreWithScreensAndSeatsDTO {
    private Long theatreId;
    private String theatreName;
    private String city;
    private List<ScreenWithSeatsDTO> screens;

}
