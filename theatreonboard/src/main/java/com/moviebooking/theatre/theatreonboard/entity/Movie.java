package com.moviebooking.theatre.theatreonboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "genre")
    private String genre;

    // Other movie-related properties and relationships

    // Constructors, getters, setters, and other methods...
}


