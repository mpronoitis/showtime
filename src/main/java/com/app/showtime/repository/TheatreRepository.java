package com.app.showtime.repository;

import com.app.showtime.domain.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

}
