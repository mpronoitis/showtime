package com.app.showtime.mapper;

import com.app.showtime.domain.Reservation;
import com.app.showtime.dto.response.ReservationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserRegisteredMapper.class, ReservationSeatMapper.class})
public interface ReservationMapper extends EntityMapper<ReservationResponseDTO, Reservation> {

  //  @Mapping(source = "showtime", target = "showtime")
    @Mapping(source = "user", target = "userRegisteredDTO")
    @Mapping(source = "reservationSeats", target = "reservationSeats")
    ReservationResponseDTO toDto(Reservation reservation);
    List<ReservationResponseDTO> toDtoList(List<Reservation> reservations);
}
