package com.amit.moviebooking.service.impl;

import com.amit.moviebooking.entity.Show;
import com.amit.moviebooking.repository.ShowRepository;
import com.amit.moviebooking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Override
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @Override
    public Show getShowById(Long showId) {
        return showRepository.findById(showId).orElse(null);
    }

    @Override
    public Show createShow(Show show) {
        // Implement show creation logic
        return showRepository.save(show);
    }

    @Override
    public Show updateShow(Long showId, Show updatedShow) {
        // Implement show update logic
        Show existingShow = showRepository.findById(showId).orElse(null);
        if (existingShow != null) {
            // Update show details
            existingShow.setMovie(updatedShow.getMovie());
            existingShow.setTheatre(updatedShow.getTheatre());
            existingShow.setShowTime(updatedShow.getShowTime());
            // Update other properties as needed
            return showRepository.save(existingShow);
        } else {
            // Show not found, handle the error accordingly
            return null;
        }
    }

    @Override
    public void deleteShow(Long showId) {
        // Implement show deletion logic
        showRepository.deleteById(showId);
    }

    // Implement other methods for CRUD operations
}

