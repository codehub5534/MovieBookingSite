package com.moviebooking.theatre.theatreonboard.messaging;

import com.moviebooking.theatre.theatreonboard.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
    private User user;
    private String message;

    // Constructors, getters, setters...
}

