package com.moviebooking.theatre.theatreonboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id", referencedColumnName = "id")
    private Theatre theatre;

    @Column(name = "show_date")
    private LocalDate showDate;

    @Column(name = "show_time")
    private LocalTime showTime;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    // Other show-related properties and relationships

    // Constructors, getters, setters, and other methods...
}


