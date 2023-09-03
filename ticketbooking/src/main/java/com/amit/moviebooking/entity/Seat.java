package com.amit.moviebooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;


@Entity
@Data
@NoArgsConstructor

public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;
    private boolean isAvailable;

    @ManyToOne
    private Show show;

    private String seatType;

    @Version
    private Long version; // Optimistic concurrency control version

    // Getters and setters
}

