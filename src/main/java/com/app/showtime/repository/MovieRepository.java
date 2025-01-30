package com.app.showtime.repository;

import com.app.showtime.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m from Movie m join  fetch m.showtimes s where FUNCTION('DATE', s.startTime) = :date ")
    List<Movie> findMoviesWithShowtimesOnDate(@Param("date")LocalDate date);
}
