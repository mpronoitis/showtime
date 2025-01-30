package com.app.showtime.dto.response;

import com.app.showtime.domain.ShowtimeSeat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ShowtimeResponseDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TheatreResponseDTO theatreResponseDTO;
    private MovieResponseDTO movieResponseDTO;
    private Set<ShowtimeSeatResponseDTO> showtimeSeats;
}
