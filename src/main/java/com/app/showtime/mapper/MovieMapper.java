package com.app.showtime.mapper;


import com.app.showtime.domain.Movie;

import com.app.showtime.dto.response.MovieResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ShowtimeMapper.class)
public interface MovieMapper extends EntityMapper<MovieResponseDTO, Movie> {

    @Mapping(source = "showtimes", target = "showtimes")
    List<MovieResponseDTO> toDTO(List<Movie> movies);
}
