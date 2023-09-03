package com.moviebooking.notification.message;

public class BookingEvent {
    private Long bookingId;
    private String email;
    private String phoneNumber;

    public BookingEvent() {
    }

    public BookingEvent(Long bookingId, String email, String phoneNumber) {
        this.bookingId = bookingId;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

