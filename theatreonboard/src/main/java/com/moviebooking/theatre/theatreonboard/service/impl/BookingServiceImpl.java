package com.moviebooking.theatre.theatreonboard.service.impl;

import com.moviebooking.theatre.theatreonboard.configuration.BookingProperties;
import com.moviebooking.theatre.theatreonboard.dto.BookingRequest;
import com.moviebooking.theatre.theatreonboard.entity.*;
import com.moviebooking.theatre.theatreonboard.exception.*;
import com.moviebooking.theatre.theatreonboard.messaging.PaymentMessageProducer;
import com.moviebooking.theatre.theatreonboard.messaging.PaymentRequest;
import com.moviebooking.theatre.theatreonboard.messaging.PaymentResponse;
import com.moviebooking.theatre.theatreonboard.repository.*;
import com.moviebooking.theatre.theatreonboard.service.*;
import com.moviebooking.theatre.theatreonboard.strategy.PricingStrategy;
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
    private TheatreRepository theatreRepository;

    @Autowired
    private SeatAvailabilityService seatAvailabilityService;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    PaymentService paymentService;

    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public Booking createBooking(BookingRequest bookingRequest) throws PaymentFailedException, SeatUnavailableException {
        // Validate input and process the booking request
        // Fetch show details
        // Check seat availability
        // Create booking
        // Update seat availability
        // Perform payment
        // Send confirmation/notification
        // Return booking ID

        Long showId = bookingRequest.getShowId();
        List<String> seatNumbers = bookingRequest.getSeatNumbers();
       // String paymentMethod = bookingRequest.getPaymentMethod();
        User dummy = new User();
        dummy.setFirstName("test");
        dummy.setEmail("testmail@yob.com");
        dummy.setLastName("ghj");
        Long theatreId = bookingRequest.getTheatreId();
      //  LocalTime showTime = bookingRequest.getShowTime();
        User user = userRepository.findById(bookingRequest.getUserId()).orElse(dummy);
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

            seatAvailabilityService.updateSeatAvailability(showId,booking.getSeats());

            booking.setUser(user);
            notificationService.sendBookingConfirmation(booking);
        } catch (PaymentFailedException e) {
            // Payment failed, rollback seat updates and delete booking
            seatAvailabilityService.rollbackSeatAvailability(booking.getSeats());
            //bookingRepository.delete(booking);

            // Notify user about payment failure
            notificationService.sendPaymentFailureNotification(booking.getUser());

            throw e; // Re-throw the exception to indicate the failure
        }

        return saveBookingWithOptimisticLock(booking);
    }

    @Transactional
    private Booking saveBookingWithOptimisticLock(Booking booking) {
        return bookingRepository.save(booking);
    }

    private PaymentRequest createPaymentRequest(Booking booking, String correlationId) {
        PaymentRequest paymentRequest = new PaymentRequest();
        // Set payment information based on booking or user input
        paymentRequest.setPaymentMethod(booking.getPaymentMethod());
        //paymentRequest.setCardNumber(booking.getCardNumber());
       // paymentRequest.setExpiryDate(booking.getExpiryDate());
      //  paymentRequest.setCvv(booking.getCvv());
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
}

