package com.moviebooking.theatre.theatreonboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private double price;


    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "seat_type")
    private String seatType;

    @org.springframework.data.annotation.Version
    private Long version; // Optimistic concurrency control version

    // Constructors, getters, and setters
}

