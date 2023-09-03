package com.moviebooking.theatre.theatreonboard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "theatres")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "theatre")
    private List<Screen> screens;

    // Other theatre-related properties and relationships

    // Constructors, getters, setters, and other methods...
}


