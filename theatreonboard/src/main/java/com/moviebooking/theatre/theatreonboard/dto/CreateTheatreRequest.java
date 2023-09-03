package com.moviebooking.theatre.theatreonboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTheatreRequest {
    private String name;
    private String city;
    private List<CreateScreenRequest> screens;

}

