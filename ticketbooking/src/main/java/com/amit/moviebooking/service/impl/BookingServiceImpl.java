package com.amit.moviebooking.service.impl;

import com.amit.moviebooking.configuration.BookingProperties;
import com.amit.moviebooking.dto.BookingRequest;
import com.amit.moviebooking.entity.*;
import com.amit.moviebooking.exception.*;
import com.amit.moviebooking.messaging.*;
import com.amit.moviebooking.repository.BookingRepository;
import com.amit.moviebooking.repository.SeatRepository;
import com.amit.moviebooking.repository.ShowRepository;
import com.amit.moviebooking.repository.TheatreRepository;
import com.amit.moviebooking.service.*;
import com.amit.moviebooking.strategy.PricingStrategy;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final PricingStrategy pricingStrategy;

    @Autowired
    public BookingServiceImpl(BookingProperties bookingProperties, List<PricingStrategy> pricingStrategies) {
        String selectedPricingStrategy = bookingProperties.getPricingStrategy();
        this.pricingStrategy = pricingStrategies.stream()
                .filter(strategy -> strategy.getClass().getSimpleName().equalsIgnoreCase(selectedPricingStrategy))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid pricing strategy: " + selectedPricingStrategy));
    }

    @Autowired
    SeatValidationService seatValidationService;

    @Autowired
    BookingCreationService bookingCreationService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private PaymentMessageProducer paymentMessageProducer;

    @Autowired
    private NotificationMessageEventProducer notificationMessageEventProducer;

    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private SeatAvailabilityService seatAvailabilityService;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    PaymentService paymentService;
    @Override
    @Transactional
    public Booking createBooking(BookingRequest bookingRequest) throws SeatUnavailableException, PaymentFailedException {
        // Validate input and process the booking request
        // Fetch show details
        // Check seat availability
        // Create booking
        // Update seat availability
        // Perform payment
        // Send confirmation/notification
        // Return booking ID

        Long showId = bookingRequest.getShowId();
        List<Integer> seatNumbers = bookingRequest.getSeatNumbers();
       // String paymentMethod = bookingRequest.getPaymentMethod();
        Long theatreId = bookingRequest.getTheatreId();
      //  LocalTime showTime = bookingRequest.getShowTime();

        // Fetch show details
        Show show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException("Show not found"));
    // validate seat availability
        seatValidationService.validateSeatAvailability(show,seatNumbers);
        // Fetch theatre details
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(() -> new TheatreNotFoundException("Theatre not found"));
        //create seat booking
        Booking booking = bookingCreationService.createBooking(show, theatre, bookingRequest.getSeatNumbers());
        // Calculate the total payment amount (you might have pricing logic here)
        double totalPayment = calculateTotalPayment(booking.getSeats().size());
        booking.setTotalPayment(totalPayment);

        try {
            paymentService.processPayment(booking);

            seatAvailabilityService.updateSeatAvailability(booking.getSeats());

            notificationService.sendBookingConfirmation(booking);
        } catch (PaymentFailedException e) {
            // Payment failed, rollback seat updates and delete booking
            seatAvailabilityService.rollbackSeatAvailability(booking.getSeats());
            //bookingRepository.delete(booking);

            // Notify user about payment failure
            notificationService.sendPaymentFailureNotification(booking.getUser());

            throw e; // Re-throw the exception to indicate the failure
        }
//        Booking booking = bookingMapper.bookingRequestToBooking(bookingRequest);
        // Additional logic for setting other fields
        // Retrieve the show associated with the booking



        // Save the updated seats and booking
      //  seatRepository.saveAll(bookedSeats);
      //  Booking createdBooking = bookingRepository.save(booking);

        // Create PaymentRequest object with a unique correlation ID
       // String correlationId = UUID.randomUUID().toString();
       // PaymentRequest paymentRequest = createPaymentRequest(booking,correlationId);


       /* try {
            paymentMessageProducer.sendPaymentMessage(paymentRequest);
        } catch (AmqpException e) {
            // Handle the exception or log it
            // Implement retry logic if desired
        }*/



        return saveBookingWithOptimisticLock(booking);
    }

    @Transactional
    private Booking saveBookingWithOptimisticLock(Booking booking) {
        return bookingRepository.save(booking);
    }

    private PaymentRequest createPaymentRequest(Booking booking,String correlationId) {
        PaymentRequest paymentRequest = new PaymentRequest();
        // Set payment information based on booking or user input
        paymentRequest.setPaymentMethod(booking.getPaymentMethod());
        paymentRequest.setCardNumber(booking.getCardNumber());
        paymentRequest.setExpiryDate(booking.getExpiryDate());
        paymentRequest.setCvv(booking.getCvv());
        paymentRequest.setAmount(booking.getTotalPayment());
        paymentRequest.setCorrelationId(correlationId);
        return paymentRequest;
    }

    private double calculateTotalPayment(int numberOfSeats) {
        // Here you can implement your pricing logic
        // For example, you might charge a base rate per seat and adjust based on movie duration
        double baseRatePerSeat = 10.0; // Adjust as needed
       // double rateMultiplier = 1.0;   // Adjust as needed

        // Calculate total payment based on number of seats and movie duration
        double totalPayment = pricingStrategy.calculatePrice(numberOfSeats, baseRatePerSeat);

        return totalPayment;
    }

    // Listener for response queue
    @RabbitListener(queues = "${response.queue}")
    public void handlePaymentResponse(PaymentResponse paymentResponse) {
        // Find booking based on correlation ID and update payment status
        Optional<Booking> bookingOptional = bookingRepository.findByCorrelationId(paymentResponse.getCorrelationId());
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();

            if (paymentResponse.isSuccess()) {
                booking.setPaymentStatus(PaymentStatus.SUCCESSFUL);
                // Send a notification asynchronously
                notificationMessageEventProducer.sendBookingMessageNotification(booking);

            } else {
                booking.setPaymentStatus(PaymentStatus.FAILED);
                notificationMessageEventProducer.sendBookingMessageNotification(booking);
            }

            bookingRepository.save(booking);
        }
    }


    @Override
    public Booking getBookingById(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        return booking.orElseThrow(() -> new BookingNotFoundException("Booking with ID " + bookingId + " not found"));
    }

    @Override
    public void allocateSeats(Long showId, List<Seat> allocatedSeats) {
        Show show = showRepository.findById(showId).orElse(null);
        if (show == null) {
            // Show not found, handle the error accordingly
            return;
        }

        // Loop through allocated seats and update availability
        for (Seat seat : allocatedSeats) {
            Seat existingSeat = seatRepository.findByShowIdAndSeatNumber(showId, seat.getSeatNumber());
            if (existingSeat != null) {
                // Seat already exists, update its availability
                existingSeat.setAvailable(seat.isAvailable());
                seatRepository.save(existingSeat);
            } else {
                // Create a new seat and associate it with the show
                seat.setShow(show);
                seatRepository.save(seat);
            }
        }
    }

    @Override
    public void updateSeats(Long showId, List<Seat> updatedSeats) {
        Show show = showRepository.findById(showId).orElse(null);
        if (show == null) {
            // Show not found, handle the error accordingly
            return;
        }

        // Loop through updated seats and update their information
        for (Seat updatedSeat : updatedSeats) {
            Seat existingSeat = seatRepository.findByShowIdAndSeatNumber(showId, updatedSeat.getSeatNumber());
            if (existingSeat != null) {
                // Update seat information
                existingSeat.setSeatType(updatedSeat.getSeatType());
                // Update other seat properties as needed
                seatRepository.save(existingSeat);
            } else {
                // Seat not found, handle the error accordingly
            }
        }
    }

    @Override
    public List<Booking> bulkBookSeats(Long showId, List<Long> seatIds) {
        // Implement bulk booking logic here
        // This might involve iterating through the seatIds and creating bookings

        List<Booking> bookings = new ArrayList<>();
        for (Long seatId : seatIds) {
            Booking booking = new Booking();
            // Populate booking details
            bookings.add(booking);
        }

        return bookingRepository.saveAll(bookings);
    }

    @Override
    public void bulkCancelBookings(List<Long> bookingIds) {
        // Implement bulk cancellation logic here
        // This might involve iterating through the bookingIds and canceling bookings

        for (Long bookingId : bookingIds) {
            Booking booking = bookingRepository.findById(bookingId).orElse(null);
            if (booking != null) {
                // Perform cancellation logic
                // Update seat availability, etc.
                bookingRepository.delete(booking);
            }
        }
    }
    // Implement other methods for CRUD operations
}

