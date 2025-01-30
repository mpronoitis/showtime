package com.app.showtime.service;

import com.app.showtime.domain.Movie;
import com.app.showtime.dto.response.MovieResponseDTO;
import com.app.showtime.mapper.MovieMapper;

import com.app.showtime.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieService {

    public final MovieRepository movieRepository;
    public final MovieMapper movieMapper;


    public List<MovieResponseDTO> getByDate(LocalDate date) {
        List<Movie> movies = movieRepository.findMoviesWithShowtimesOnDate(date);


        return movieMapper.toDTO(movies);


    }
}
