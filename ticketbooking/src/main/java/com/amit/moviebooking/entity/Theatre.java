package com.amit.moviebooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private Long phoneNumber;
    private String emailId;
    private String address;
    private List<Show> shows;
// select moviename,theatre,List<Shows> by city and movie name
    // get All theatres by city and movieName;
    // Theatre -- Moviename,List<Shows>
    // Getters and setters
}
