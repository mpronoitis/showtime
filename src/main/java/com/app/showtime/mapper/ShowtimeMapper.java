package com.app.showtime.mapper;

import com.app.showtime.domain.Seat;
import com.app.showtime.domain.Showtime;
import com.app.showtime.dto.response.ShowtimeResponseDTO;
import com.app.showtime.dto.response.ShowtimeSeatResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {MovieMapper.class,TheatreMapper.class, ShowtimeSeatMapper.class})
public interface ShowtimeMapper extends EntityMapper<ShowtimeResponseDTO, Showtime> {
    @Mapping(source = "theatre", target = "theatreResponseDTO")
    @Mapping(source = "movie", target = "movieResponseDTO")
            @Mapping(source = "showtimeSeats", target = "showtimeSeats")
   // @Mapping(target = "showtimeSeats", source = "showtimeSeats") // Mapping the Set<ShowtimeSeat>
    ShowtimeResponseDTO toDto(Showtime showtime);


}
