package com.app.showtime.repository;

import com.app.showtime.domain.Showtime;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository  extends JpaRepository<Showtime, Long> {
}
