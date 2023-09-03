package com.moviebooking.notification.controller;

import com.moviebooking.notification.message.EmailRequest;
import com.moviebooking.notification.message.SMSRequest;
import com.moviebooking.notification.message.ShortURLRequest;
import com.moviebooking.notification.message.ShortURLResponse;
import com.moviebooking.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send-sms")
    public ResponseEntity<Void> sendSMS(@RequestBody SMSRequest smsRequest) {
        notificationService.sendSMS(smsRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailRequest emailRequest) {
        notificationService.sendEmail(emailRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/generate-short-url")
    public ResponseEntity<ShortURLResponse> generateShortURL(@RequestBody ShortURLRequest shortURLRequest) {
        String shortURL = notificationService.generateShortURL(shortURLRequest.getOriginalURL());
        ShortURLResponse response = new ShortURLResponse(shortURL);
        return ResponseEntity.ok(response);
    }
}

