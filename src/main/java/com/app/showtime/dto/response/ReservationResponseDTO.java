package com.app.showtime.dto.response;

import com.app.showtime.dto.ReservationSeatDTO;
import com.app.showtime.enumeration.ReservationTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ReservationResponseDTO {

    private Long id;
  //  private ShowtimeResponseDTO showtime;
    private UserRegisteredDTO userRegisteredDTO;
    private LocalDateTime reservationTime;
    private ReservationTypeEnum status;
    private Set<ReservationSeatDTO> reservationSeats;

}
