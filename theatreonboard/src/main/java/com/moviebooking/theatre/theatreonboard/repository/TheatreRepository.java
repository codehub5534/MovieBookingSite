package com.moviebooking.theatre.theatreonboard.repository;

import com.moviebooking.theatre.theatreonboard.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Long> {
    @Query("SELECT DISTINCT t FROM Theatre t WHERE t.city = :city")
    List<Theatre> findAllTheatresInCityWithScreensAndSeats(@Param("city") String city);

    List<Theatre> findByCity(String city);
}
