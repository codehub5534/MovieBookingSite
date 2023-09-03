package com.amit.moviebooking.messaging;

import com.amit.moviebooking.entity.Booking;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationMessageEventProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.notification.routingKey}")
    private String routingKey;

    public void sendBookingMessageNotification(Booking booking) {
        BookingEvent bookingEvent = new BookingEvent(booking.getId(), booking.getUser().getEmail(), booking.getUser().getPhoneNumber(), "test");
        rabbitTemplate.convertAndSend("booking-events", bookingEvent);
       // rabbitTemplate.convertAndSend(exchange, routingKey, bookingEvent);
    }
}
