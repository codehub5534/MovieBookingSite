package com.moviebooking.theatre.theatreonboard.service.impl;

import com.moviebooking.theatre.theatreonboard.dto.*;
import com.moviebooking.theatre.theatreonboard.entity.Screen;
import com.moviebooking.theatre.theatreonboard.entity.Seat;
import com.moviebooking.theatre.theatreonboard.entity.Theatre;
import com.moviebooking.theatre.theatreonboard.exception.TheatreNotFoundException;
import com.moviebooking.theatre.theatreonboard.repository.TheatreRepository;
import com.moviebooking.theatre.theatreonboard.service.ScreenService;
import com.moviebooking.theatre.theatreonboard.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ScreenService screenService;

    public Theatre createTheatre(Theatre theatre) {
        // Add logic to create a new theatre
        return theatreRepository.save(theatre);
    }

    @Override
    @Transactional
    public void createTheatreWithScreensAndSeats(CreateTheatreRequest request) {
        // Create a Theatre
        Theatre theatre = new Theatre();
        theatre.setName(request.getName());
        theatre.setCity(request.getCity());

        // Save the Theatre
        theatreRepository.save(theatre);

        // Create screens and seats for the Theatre
        screenService.createScreensWithSeats(request.getScreens(), theatre);

    }

    @Override
    public List<TheatreWithScreensAndSeatsDTO> getAllTheatresInCityWithScreensAndSeats(String city) {
        List<Theatre> theatres = theatreRepository.findAllTheatresInCityWithScreensAndSeats(city);
        return mapTheatresToDTOs(theatres);
    }

    private List<TheatreWithScreensAndSeatsDTO> mapTheatresToDTOs(List<Theatre> theatres) {
        // Implement mapping logic here to convert Theatre entities to DTOs
        List<TheatreWithScreensAndSeatsDTO> theatreDTOs = new ArrayList<>();

        for (Theatre theatre : theatres) {
            TheatreWithScreensAndSeatsDTO theatreDTO = new TheatreWithScreensAndSeatsDTO();
            theatreDTO.setTheatreId(theatre.getId());
            theatreDTO.setTheatreName(theatre.getName());
            theatreDTO.setCity(theatre.getCity());

            // Map screens and seats for the theatre
            if(theatre.getScreens()!=null) {
                List<ScreenWithSeatsDTO> screensDTOs = mapScreensToDTOs(theatre.getScreens());
                theatreDTO.setScreens(screensDTOs);
            }

            theatreDTOs.add(theatreDTO);
        }

        return theatreDTOs;
    }

    private List<ScreenWithSeatsDTO> mapScreensToDTOs(List<Screen> screens) {
        List<ScreenWithSeatsDTO> screensDTOs = new ArrayList<>();

        for (Screen screen : screens) {
            ScreenWithSeatsDTO screenDTO = new ScreenWithSeatsDTO();
            screenDTO.setScreenId(screen.getId());
            screenDTO.setScreenName(screen.getName());

            // Map seats for the screen
            List<SeatDTO> seatsDTOs = mapSeatsToDTOs(screen.getSeats());
            screenDTO.setSeats(seatsDTOs);

            screensDTOs.add(screenDTO);
        }

        return screensDTOs;
    }

    private List<SeatDTO> mapSeatsToDTOs(List<Seat> seats) {
        List<SeatDTO> seatsDTOs = new ArrayList<>();

        for (Seat seat : seats) {
            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setSeatId(seat.getId());
            seatDTO.setSeatNumber(seat.getSeatNumber());
            seatDTO.setSeatCategory(seat.getCategory());
            seatDTO.setPrice(seat.getPrice());

            seatsDTOs.add(seatDTO);
        }

        return seatsDTOs;
    }


    @Override
    public TheatreWithScreensAndSeatsDTO findTheatreById(Long id) throws TheatreNotFoundException {
        Optional<Theatre> theatreOptional= theatreRepository.findById(id);
        if(theatreOptional.isPresent()){
            return mapTheatresToDTOs(Collections.singletonList(theatreOptional.get())).get(0);
        }
        throw new TheatreNotFoundException("Theatre with given id "+id +" does not exist.");
    }


}

