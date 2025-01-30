package com.app.showtime.dto.response;

import com.app.showtime.dto.ShowtimeDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class MovieResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Integer duration;
    private Set<ShowtimeDTO> showtimes;

}
