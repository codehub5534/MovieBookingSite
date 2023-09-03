package com.amit.moviebooking.repository;

import com.amit.moviebooking.entity.Seat;
import com.amit.moviebooking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findByShowId(Long showId);

    Seat findByShowIdAndSeatNumber(Long showId, String seatNumber);

    List<Seat> findByShowAndSeatNumberIn(Show show, List<Integer> seatNumbers);
}
