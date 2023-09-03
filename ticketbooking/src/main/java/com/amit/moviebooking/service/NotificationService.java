package com.amit.moviebooking.service;

import com.amit.moviebooking.entity.Booking;
import com.amit.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendBookingConfirmation(Booking booking) {
        User user = booking.getUser();
        String bookingId = String.valueOf(booking.getId());

        String message = "Dear " + user.getFirstName() + ", your booking with ID " + bookingId + " has been confirmed. Enjoy the show!";

        // Send the notification using different channels (e.g., SMS, email)
        sendSMS(user.getPhoneNumber(), message);
        sendEmail(user.getEmail(), "Booking Confirmation", message);
    }

    private void sendSMS(String phoneNumber, String message) {
        // Simulate sending an SMS notification
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }

    private void sendEmail(String emailAddress, String subject, String message) {
        // Simulate sending an email notification
        System.out.println("Sending email to " + emailAddress + " with subject '" + subject + "': " + message);
    }

    public void sendPaymentFailureNotification(User user) {
        String message = "Dear " + user.getFirstName() + ", your payment for the booking has failed. Please try again or contact customer support.";

        // Send the payment failure notification using different channels (e.g., SMS, email)
        sendSMS(user.getPhoneNumber(), message);
        sendEmail(user.getEmail(), "Payment Failure", message);
    }
}

