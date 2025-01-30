package com.app.showtime.dto.response;

import com.app.showtime.enumeration.SeatTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowtimeSeatResponseDTO {

    private Long id;
    private SeatResponseDTO seatResponseDTO; // Assuming you have a Seat entity/DTO
    private SeatTypeEnum status; // Enum for Seat status
}
