package com.moviebooking.notification.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {
    private String emailAddress;
    private String subject;
    private String content;

    // Constructors, getters, setters...
}
