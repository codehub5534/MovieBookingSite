package com.moviebooking.theatre.theatreonboard.service.impl;

import com.moviebooking.theatre.theatreonboard.dto.*;
import com.moviebooking.theatre.theatreonboard.entity.*;
import com.moviebooking.theatre.theatreonboard.exception.MovieNotFoundException;
import com.moviebooking.theatre.theatreonboard.exception.TheatreNotFoundException;
import com.moviebooking.theatre.theatreonboard.repository.*;
import com.moviebooking.theatre.theatreonboard.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @Override
    public Show getShowById(Long showId) {
        return showRepository.findById(showId).orElse(null);
    }


    @Override
    public List<Show> createMultipleShows(CreateShowsRequest createShowsRequest) {
        // Validate inputs, fetch movie and theatre details

        Movie movie = movieRepository.findById(createShowsRequest.getMovieId())
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        Theatre theatre = theatreRepository.findById(createShowsRequest.getTheatreId())
                .orElseThrow(() -> new TheatreNotFoundException("Theatre not found"));

        // Iterate over show times and create Show objects
        List<Show> createdShows = new ArrayList<>();
        for (LocalTime showTime : createShowsRequest.getShowTimes()) {
            Show show = new Show();
            show.setMovie(movie);
            show.setTheatre(theatre);
            Screen screenShowing =theatre.getScreens().stream().filter(scr->scr.getId()
                    ==createShowsRequest.getScreenId()).findFirst().get();
            show.setScreen(screenShowing);
            show.setShowDate(createShowsRequest.getDate());
            show.setShowTime(showTime);
            // Additional show properties can be set here

            createdShows.add(show);
        }

        // Save the created shows to the repository
        return showRepository.saveAll(createdShows);
    }
    @Override
    public TheatreShowsDTO getAllShowsForMovieInTheatreAndDate(Long movieId, Long theatreId, LocalDate date) {
        // Implement the logic to fetch shows by movie, theatre, and date from the repository
        // For example:
         List<Show> shows = showRepository.findByMovieIdAndTheatreIdAndShowDate(movieId, theatreId, date);
        Theatre theatre = theatreRepository.findById(theatreId).get();
        TheatreShowsDTO theatreShow = new TheatreShowsDTO();

        List<ScreenShowsDTO> screenShows = new ArrayList<>();

            for (Show show : shows) {
                ScreenShowsDTO screenShow = new ScreenShowsDTO();
                screenShow.setScreenId(show.getScreen().getId());
                screenShow.setShowTime(show.getShowTime());
                // Add other show details as needed
                screenShows.add(screenShow);
            }

            theatreShow.setTheatreId(theatreId);
            theatreShow.setTheatreName(theatre.getName());
            theatreShow.setShows(screenShows);

        return theatreShow;
    }

    @Override
    public void updateShows(List<UpdatedShowDTO> updatedShows) {
        for (UpdatedShowDTO updatedShow : updatedShows) {
            Long showId = updatedShow.getShowId();
            LocalTime newShowTime = updatedShow.getNewShowTime();

            // Fetch the show from the database by showId
            Optional<Show> showOptional = showRepository.findById(showId);

            if (showOptional.isPresent()) {
                Show show = showOptional.get();
                // Update the show's showTime with the newShowTime
                show.setShowTime(newShowTime);
                // Save the updated show back to the database
                showRepository.save(show);
            }
        }
    }

    @Override
    public void createMultipleShows(CreateMultipleShowsRequest request) {
        // Iterate through the request and create shows
       Theatre theatre=theatreRepository.findById(request.getTheatreId()).orElse(null);
        for (ShowRequest showRequest : request.getShowRequests()) {
            Show show = new Show();
            Movie movie = movieRepository.findById(showRequest.getMovieId()).orElse(null);
            Screen screen= screenRepository.findById(showRequest.getScreen().getScreenId()).orElse(null);
            show.setMovie(movie);
            show.setTheatre(theatre);
            show.setShowDate(request.getDate());
            show.setShowTime(showRequest.getShowTime());
            show.setScreen(screen);
            showRepository.save(show);

            // Create seats for the show
          /*  for (SeatRequest seatRequest : showRequest.getScreen().getSeats()) {
                Seat seat = new Seat();
                seat.setSeatNumber(seatRequest.getSeatNumber());
                seat.setCategory(seatRequest.getCategory());
                seat.setPrice(seatRequest.getPrice());
                seat.setScreen(screen);
                seat.setShow(show);
                seatRepository.save(seat);
            }*/


        }
    }
    @Override
    public void deleteShows(List<Long> showIds) {
        // Retrieve shows to delete in a single query
        List<Show> showsToDelete = showRepository.findAllById(showIds);

        // Delete the retrieved shows in a single batch operation
        showRepository.deleteAll(showsToDelete);
    }

    @Override
    public List<TheatreShowsDTO> getAllShowsForCityAndMovie(String city, Long movieId, LocalDate date) {
        List<Theatre> theatres = theatreRepository.findAllTheatresInCityWithScreensAndSeats(city);
        List<TheatreShowsDTO> theatreShows = new ArrayList<>();

        for (Theatre theatre : theatres) {
            List<Show> shows = showRepository.findByMovieIdAndTheatreIdAndShowDate(movieId, theatre.getId(), date);
            List<ScreenShowsDTO> screenShows = new ArrayList<>();

            for (Show show : shows) {
                ScreenShowsDTO screenShow = new ScreenShowsDTO();
                screenShow.setScreenId(show.getScreen().getId());
                screenShow.setShowTime(show.getShowTime());
                // Add other show details as needed
                screenShows.add(screenShow);
            }

            TheatreShowsDTO theatreShow = new TheatreShowsDTO();
            theatreShow.setTheatreId(theatre.getId());
            theatreShow.setTheatreName(theatre.getName());
            theatreShow.setShows(screenShows);
            theatreShows.add(theatreShow);
        }

        return theatreShows;
    }


}

