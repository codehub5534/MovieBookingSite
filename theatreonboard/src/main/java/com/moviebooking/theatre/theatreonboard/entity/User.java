package com.moviebooking.theatre.theatreonboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // You should hash and secure passwords

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;


    // Getters and setters

    // Other properties and methods, e.g., roles, etc.
}

