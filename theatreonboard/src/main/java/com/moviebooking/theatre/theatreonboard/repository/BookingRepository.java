package com.moviebooking.theatre.theatreonboard.repository;

import com.moviebooking.theatre.theatreonboard.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    //Optional<Booking> findByCorrelationId(String correlationId);
}
