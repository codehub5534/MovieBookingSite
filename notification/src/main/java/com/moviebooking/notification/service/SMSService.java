package com.moviebooking.notification.service;

import com.moviebooking.notification.message.SMSRequest;

public interface SMSService {
    void sendSMS(SMSRequest request);

    void sendSMS(String phoneNumber, String message);
}
