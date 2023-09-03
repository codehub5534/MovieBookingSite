package com.moviebooking.theatre.theatreonboard.service.impl;

import com.moviebooking.theatre.theatreonboard.dto.CreateScreenRequest;
import com.moviebooking.theatre.theatreonboard.dto.CreateSeatRequest;
import com.moviebooking.theatre.theatreonboard.entity.Screen;
import com.moviebooking.theatre.theatreonboard.entity.Seat;
import com.moviebooking.theatre.theatreonboard.entity.Theatre;
import com.moviebooking.theatre.theatreonboard.exception.TheatreNotFoundException;
import com.moviebooking.theatre.theatreonboard.repository.ScreenRepository;
import com.moviebooking.theatre.theatreonboard.repository.SeatRepository;
import com.moviebooking.theatre.theatreonboard.repository.TheatreRepository;
import com.moviebooking.theatre.theatreonboard.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    private  ScreenRepository screenRepository;
    @Autowired
    private  TheatreRepository theatreRepository;
    @Autowired
    private SeatRepository seatRepository;


    @Override
    public void createScreen(CreateScreenRequest request) {
        Screen screen = new Screen();
        screen.setName(request.getName());
        screen.setCapacity(request.getCapacity());

        Theatre theatre = theatreRepository.findById(request.getTheatreId())
                .orElseThrow(() -> new TheatreNotFoundException("Theatre not found"));

        screen.setTheatre(theatre);

        screenRepository.save(screen);
    }

    @Override
    @Transactional
    public void createScreensWithSeats(List<CreateScreenRequest> screenRequests, Theatre theatre) {
        List<Screen> screens = new ArrayList<>();
        List<Seat> seats = new ArrayList<>();

        for (CreateScreenRequest screenRequest : screenRequests) {
            // Create a Screen
            Screen screen = new Screen();
            screen.setName(screenRequest.getName());
            screen.setTheatre(theatre);
            screen.setCapacity(screenRequest.getCapacity());

            // Add the Screen to the list
            screens.add(screen);

            // Create seats for the Screen
            for (CreateSeatRequest seatRequest : screenRequest.getSeats()) {
                Seat seat = new Seat();
                seat.setScreen(screen);
                seat.setSeatNumber(seatRequest.getSeatNumber());
                seat.setCategory(seatRequest.getSeatCategory());
                seat.setPrice(seatRequest.getSeatPrice());

                // Add the Seat to the list
                seats.add(seat);
            }
        }

        // Save screens and seats in batches
        screenRepository.saveAll(screens);
        seatRepository.saveAll(seats);
    }
}

