package com.moviebooking.payment.controller;

import com.moviebooking.payment.request.PaymentRequest;
import com.moviebooking.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {
        // Process the payment request and return a response
        String paymentResult = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(paymentResult);
    }
}

