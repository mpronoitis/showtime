package com.app.showtime.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TheatreResponseDTO {

    public Long id;
    private String name;
    private String location;


}
