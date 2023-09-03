package com.moviebooking.theatre.theatreonboard.messaging;

import lombok.Data;

@Data
public class BookingEvent {
    private Long bookingId;
    private String email;
    private String phoneNumber;

    private String correlationId;

    public BookingEvent() {
    }

    public BookingEvent(Long bookingId, String email, String phoneNumber,String correlationId) {
        this.bookingId = bookingId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.correlationId=correlationId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

