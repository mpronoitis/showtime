package com.app.showtime.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowtimeRequestDTO {

    @NotNull(message = "Start time cannot be null")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$",
            message = "Invalid date-time format. Use 'yyyy-MM-dd HH:mm'"
    )
    private String startTime;


    @NotNull
    private Long theatreId;

    @NotNull
    private Long movieId;
}
