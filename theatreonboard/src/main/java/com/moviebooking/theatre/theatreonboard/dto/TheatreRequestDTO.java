package com.moviebooking.theatre.theatreonboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TheatreRequestDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String city;
}
