package com.moviebooking.theatre.theatreonboard.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
public class BookingRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long showId;

    private String correlationId;

    private Long theatreId;

    private LocalTime showTime;

    @NotNull
    @Min(value = 1, message = "At least 1 seat must be booked")
    private Integer numberOfSeats;

    @NotNull
    @Future(message = "Booking date must be in the future")
    private Date bookingDate;

    // Payment-related fields
    @NotNull
    private String paymentMethod;

    @NotNull
    private String cardNumber;

    private List<String> seatNumbers;

    @NotNull
    private String expiryDate;

    @NotNull
    private String cvv;

    // Getters and setters

    // Additional fields and validation annotations as needed
}


