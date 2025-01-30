package com.app.showtime.repository;

import com.app.showtime.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select re from Reservation re where re.user.id = :userId")
    List<Reservation> findReservationByUserId(Long userId);
}
