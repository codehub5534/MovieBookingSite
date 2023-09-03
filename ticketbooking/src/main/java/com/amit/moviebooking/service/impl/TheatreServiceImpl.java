package com.amit.moviebooking.service.impl;

import com.amit.moviebooking.entity.Theatre;
import com.amit.moviebooking.repository.TheatreRepository;
import com.amit.moviebooking.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public Theatre getTheatreById(Long theatreId) {
        return theatreRepository.findById(theatreId).orElse(null);
    }

    // Implement other methods for CRUD operations
}

