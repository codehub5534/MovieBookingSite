package com.moviebooking.notification.service;

import com.moviebooking.notification.message.BookingEvent;
import com.moviebooking.notification.message.EmailRequest;
import com.moviebooking.notification.message.SMSRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private SMSService smsService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private URLShortenerService urlShortenerService;

    public void sendSMS(String phoneNumber, String message) {
        // Call the SMS service API to send SMS
        smsService.sendSMS(phoneNumber, message);
    }

    public void sendEmail(String emailAddress, String subject, String content) {
        // Call the email service API to send email
        emailService.sendEmail(emailAddress, subject, content);
    }

    public String generateShortURL(String originalURL) {
        // Call the URL shortener service API to generate short URL
        return urlShortenerService.generateShortURL(originalURL);
    }

    public void sendSMS(SMSRequest smsRequest) {
        smsService.sendSMS(smsRequest);
    }

    public void sendEmail(EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest);
    }
}

