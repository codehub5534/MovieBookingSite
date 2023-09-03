package com.moviebooking.theatre.theatreonboard.service;

import com.moviebooking.theatre.theatreonboard.dto.CreateScreenRequest;
import com.moviebooking.theatre.theatreonboard.entity.Theatre;

import java.util.List;

public interface ScreenService {
    public void createScreen(CreateScreenRequest request);
    public void createScreensWithSeats(List<CreateScreenRequest> screenRequests, Theatre theatre);
}
