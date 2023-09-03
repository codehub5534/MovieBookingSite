package com.moviebooking.moviesearch.dto;

import com.moviebooking.moviesearch.entity.Show;
import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchResponse {
    String movieName;
    String city;

    String theatreName;

    String language;

    List<Show> shows;
}
