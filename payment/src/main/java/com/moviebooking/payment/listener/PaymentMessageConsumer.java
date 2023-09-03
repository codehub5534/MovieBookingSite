package com.moviebooking.payment.listener;

import com.moviebooking.payment.request.PaymentRequest;
import com.moviebooking.payment.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMessageConsumer {

    @Autowired
    PaymentService paymentService;
    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receivePaymentMessage(PaymentRequest paymentRequest) {
        // Process payment request
        String paymentResult = paymentService.processPayment(paymentRequest);
        // You can log or handle the payment result as needed
    }
}

