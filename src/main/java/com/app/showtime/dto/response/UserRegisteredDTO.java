package com.app.showtime.dto.response;

import com.app.showtime.dto.UserRegistryDTO;
import lombok.Data;

@Data
public class UserRegisteredDTO {

    private Long id;
    private String username;
    private UserRegistryDTO registry;

}
