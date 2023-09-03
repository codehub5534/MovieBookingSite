package com.moviebooking.theatre.theatreonboard.messaging;

import com.moviebooking.theatre.theatreonboard.entity.Booking;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMessageEventProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.notification.routingKey}")
    private String routingKey;

    public void sendBookingMessageNotification(Booking booking) {
        BookingEvent bookingEvent = new BookingEvent(booking.getId(), booking.getUser().getEmail(), booking.getUser().getPhoneNumber(), getCorrelationId(booking));
        rabbitTemplate.convertAndSend("booking-events", bookingEvent);
       // rabbitTemplate.convertAndSend(exchange, routingKey, bookingEvent);
    }

    private String getCorrelationId(Booking booking){
        //logic can be implemented in seperate Microservice with proper sequening algo,its temporary
        return String.valueOf(LocalDateTime.now())+booking.getId();
    }
}
