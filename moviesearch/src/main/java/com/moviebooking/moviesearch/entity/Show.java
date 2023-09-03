package com.moviebooking.moviesearch.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Show {

    private Integer id;

    private LocalDate startDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String theaterName;

    private String movieTitle;
}
