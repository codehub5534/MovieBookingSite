package com.moviebooking.notification.listener;

import com.moviebooking.notification.message.BookingEvent;
import com.moviebooking.notification.message.NotificationMessage;
import com.moviebooking.notification.service.EmailService;
import com.moviebooking.notification.service.SMSService;
import com.moviebooking.notification.service.URLShortenerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @Autowired
    private SMSService smsService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private URLShortenerService urlShortenerService;

    @RabbitListener(queues = "booking-events")
    public void handleBookingEvent(BookingEvent bookingEvent) {
        // Send booking notification
        String bookingDetails = "Booking details: ..."; // Retrieve booking details
        emailService.sendEmail(bookingEvent.getEmail(), "Booking Confirmation", bookingDetails);
        smsService.sendSMS(bookingEvent.getPhoneNumber(), "Your booking is confirmed. " + bookingDetails);

        // Generate and send URL shortening notification
        String ticketURL = "https://..."; // Generate ticket URL
        String shortURL = urlShortenerService.shortenURL(ticketURL).getShortURL();
        emailService.sendEmail(bookingEvent.getEmail(), "Ticket Download Link", "Download your ticket: " + shortURL);
    }
}

