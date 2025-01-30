package com.app.showtime.mapper;

import com.app.showtime.domain.ReservationSeat;
import com.app.showtime.dto.ReservationSeatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ShowtimeSeatMapper.class)
public interface ReservationSeatMapper extends EntityMapper<ReservationSeatDTO, ReservationSeat> {
    @Mapping(source = "showtimeSeat", target = "showtimeSeat")
    ReservationSeatDTO toDto(ReservationSeat reservationSeat);
}
