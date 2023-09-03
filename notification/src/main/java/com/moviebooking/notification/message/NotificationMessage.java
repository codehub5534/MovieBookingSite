package com.moviebooking.notification.message;

import com.moviebooking.notification.entity.User;
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

