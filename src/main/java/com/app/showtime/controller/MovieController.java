package com.app.showtime.controller;

import com.app.showtime.domain.Movie;
import com.app.showtime.dto.response.MovieResponseDTO;
import com.app.showtime.dto.response.ShowtimeResponseDTO;
import com.app.showtime.service.MovieService;
import com.app.showtime.shared.rest.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class MovieController {

    public final MovieService movieService;

    @GetMapping("/movies/showtimes/{date}")
    public ApiResponse<List<MovieResponseDTO>> getByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return new ApiResponse.Builder<List<MovieResponseDTO>>().payload(movieService.getByDate(date)).build();
    }

}
