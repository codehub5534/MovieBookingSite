package com.moviebooking.theatre.theatreonboard.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentMessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    public void sendPaymentMessage(PaymentRequest paymentRequest) {
        rabbitTemplate.convertAndSend(exchange, routingKey, paymentRequest);
    }
}

