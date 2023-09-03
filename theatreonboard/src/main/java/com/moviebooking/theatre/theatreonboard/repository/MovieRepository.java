package com.moviebooking.theatre.theatreonboard.repository;

import com.moviebooking.theatre.theatreonboard.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
