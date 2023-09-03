package com.moviebooking.theatre.theatreonboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequest {
    private String seatNumber;
    private String category;
    private double price;
}
