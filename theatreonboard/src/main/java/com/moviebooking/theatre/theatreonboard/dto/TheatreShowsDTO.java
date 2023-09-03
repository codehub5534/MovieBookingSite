package com.moviebooking.theatre.theatreonboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheatreShowsDTO {
    private Long theatreId;
    private String theatreName;
    private List<ScreenShowsDTO> shows;
}
