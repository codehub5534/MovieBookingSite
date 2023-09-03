package com.moviebooking.moviesearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchRequest {
    String movieName;
    String city;

    String showDate;

    String language;

    String genre;
}
