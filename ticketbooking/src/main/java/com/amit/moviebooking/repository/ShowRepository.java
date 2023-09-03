package com.amit.moviebooking.repository;

import com.amit.moviebooking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {

    @Query("SELECT s FROM Show s JOIN s.movie m WHERE m.title = ?1 AND s.date = ?2")
    List<Show> findByMovieNameAndDate(String movieName, LocalDateTime date);
}
