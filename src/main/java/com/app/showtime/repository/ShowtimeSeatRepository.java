package com.app.showtime.repository;

import com.app.showtime.domain.ShowtimeSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShowtimeSeatRepository extends JpaRepository<ShowtimeSeat, Long> {

    @Query("select sts from ShowtimeSeat sts where sts.showtime.id = :showtimeId and sts.status = 'available' ")
    Optional<List<ShowtimeSeat>> findByShowtimeIdAndStatusTrue(Long showtimeId);

    @Query("select sts from ShowtimeSeat sts where sts.showtime.id = :showtimeId and sts.seat.seatNumber IN :seatNames and sts.status = 'available'")
    List<ShowtimeSeat> findShowtimeSeatsByShowtimeIdAndSeatNumber(Long showtimeId, List<String> seatNames);
}
