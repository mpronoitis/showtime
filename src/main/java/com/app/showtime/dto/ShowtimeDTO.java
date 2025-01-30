package com.app.showtime.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class ShowtimeDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
   // private Set<ShowtimeSeatResponseDTO> showtimeSeats;
}
