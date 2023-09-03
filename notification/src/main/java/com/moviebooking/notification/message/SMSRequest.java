package com.moviebooking.notification.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMSRequest {
    private String phoneNumber;
    private String message;

    // Constructors, getters, setters...
}
