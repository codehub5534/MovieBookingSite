package com.moviebooking.theatre.theatreonboard.repository;

import com.moviebooking.theatre.theatreonboard.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {

    @Query("SELECT s FROM Show s WHERE s.movie.id = :movieId AND s.theatre.id = :theatreId AND s.showDate = :showDate")
    List<Show> findByMovieIdAndTheatreIdAndShowDate(
            @Param("movieId") Long movieId,
            @Param("theatreId") Long theatreId,
            @Param("showDate") LocalDate showDate);
}
