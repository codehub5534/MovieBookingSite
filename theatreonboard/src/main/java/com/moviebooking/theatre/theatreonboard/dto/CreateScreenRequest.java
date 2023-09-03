package com.moviebooking.theatre.theatreonboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateScreenRequest {
    private String name;
    private int capacity;
    private Long theatreId;
    private List<CreateSeatRequest> seats;
}

