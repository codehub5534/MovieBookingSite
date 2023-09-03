package com.amit.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Entity
@Data
@NoArgsConstructor
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

