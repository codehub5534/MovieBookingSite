package com.moviebooking.notification.service.impl;

import com.moviebooking.notification.message.EmailRequest;
import com.moviebooking.notification.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmail(EmailRequest request) {

    }

    @Override
    public void sendEmail(String emailAddress, String subject, String content) {

    }
}
