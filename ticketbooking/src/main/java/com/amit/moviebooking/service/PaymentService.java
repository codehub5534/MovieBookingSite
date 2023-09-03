package com.amit.moviebooking.service;

import com.amit.moviebooking.entity.Booking;
import com.amit.moviebooking.entity.PaymentStatus;
import com.amit.moviebooking.exception.PaymentFailedException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void processPayment(Booking booking) throws PaymentFailedException {
        double totalPayment = calculateTotalPayment(booking);
        String paymentMethod = booking.getPaymentMethod();

        // Call the payment gateway API to process the payment
        boolean paymentSuccess = callPaymentGateway(totalPayment, paymentMethod);

        if (paymentSuccess) {
            booking.setPaymentStatus(PaymentStatus.PAID);
        } else {
            booking.setPaymentStatus(PaymentStatus.FAILED);
            throw new PaymentFailedException("Payment processing failed");
        }
    }

    private double calculateTotalPayment(Booking booking) {
        // Calculate the total payment based on booking details, discounts, etc.
        // You can implement your pricing logic here
        return booking.getTotalPayment();
    }

    private boolean callPaymentGateway(double totalPayment, String paymentMethod) {
        // Call the external payment gateway API and return the success status
        // This is a placeholder and should be implemented based on the chosen payment gateway
        return true;  // Assuming payment is successful for demonstration purposes
    }
}

