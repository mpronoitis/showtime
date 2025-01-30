package com.app.showtime.mapper;

import com.app.showtime.domain.Seat;
import com.app.showtime.dto.response.SeatResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeatMapper extends EntityMapper<SeatResponseDTO, Seat> {
}
