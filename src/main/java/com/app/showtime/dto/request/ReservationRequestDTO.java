package com.app.showtime.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReservationRequestDTO {

    @NotNull(message = "Provide a showtimeId to reserve your seat")
    private Long showtimeId;

    @NotNull(message = "Provide a list of seats you want to reserve")
    private List<String> seatNames;


}
