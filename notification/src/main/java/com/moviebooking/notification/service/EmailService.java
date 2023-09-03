package com.moviebooking.notification.service;

import com.moviebooking.notification.message.EmailRequest;

public interface EmailService {
    void sendEmail(EmailRequest request);

    void sendEmail(String emailAddress, String subject, String content);
}
