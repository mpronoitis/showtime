package com.app.showtime.mapper;

import com.app.showtime.domain.ShowtimeSeat;
import com.app.showtime.dto.response.ShowtimeResponseDTO;
import com.app.showtime.dto.response.ShowtimeSeatResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.crypto.spec.PSource;
import java.util.List;

@Mapper(componentModel = "spring", uses = SeatMapper.class)
public interface ShowtimeSeatMapper extends EntityMapper<ShowtimeSeatResponseDTO, ShowtimeSeat> {

    @Mapping(source = "seat", target = "seatResponseDTO")
    ShowtimeSeatResponseDTO toDto(ShowtimeSeat showtimeSeat);
    List<ShowtimeSeatResponseDTO> toDtoList(List<ShowtimeSeat> showtimeSeats);
}
