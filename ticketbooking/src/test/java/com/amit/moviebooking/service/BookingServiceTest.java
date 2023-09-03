package com.amit.moviebooking.service;

import com.amit.moviebooking.dto.BookingRequest;
import com.amit.moviebooking.entity.Booking;
import com.amit.moviebooking.repository.BookingRepository;
import com.amit.moviebooking.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RabbitTemplate rabbitTemplate; // RabbitMQ template

    @Test
    public void testCreateBooking() {
        BookingRequest booking = new BookingRequest();
        booking.setShowId(12l);
        booking.setCorrelationId("123");
        booking.setNumberOfSeats(5);
        Booking booking1 =new Booking();
        Mockito.when(bookingRepository.save(Mockito.any(Booking.class))).thenReturn(booking1);

      //  Booking createdBooking = bookingService.createBooking(booking);

     //   assertNotNull(createdBooking);
        // Add more assertions based on your specific requirements
    }

    // Add more test methods for other service methods if needed
}

