package com.moviebooking.theatre.theatreonboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TheatreResponseDTO {

    private Long id;

    private String name;

    private String city;
}
