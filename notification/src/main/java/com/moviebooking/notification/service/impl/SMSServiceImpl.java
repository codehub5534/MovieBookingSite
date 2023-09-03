package com.moviebooking.notification.service.impl;

import com.moviebooking.notification.message.SMSRequest;
import com.moviebooking.notification.service.SMSService;
import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements SMSService {
    @Override
    public void sendSMS(SMSRequest request) {

    }

    @Override
    public void sendSMS(String phoneNumber, String message) {

    }
}
