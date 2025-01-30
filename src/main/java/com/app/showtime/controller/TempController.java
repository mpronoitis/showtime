package com.app.showtime.controller;

import com.app.showtime.domain.Permission;
import com.app.showtime.shared.rest.model.ApiResponse;
import jakarta.annotation.security.RolesAllowed;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TempController {

    @GetMapping("/all")
    //@RolesAllowed(Permission.ADD_MOVIE)
    public ApiResponse<String> getAll() {
        return new ApiResponse.Builder<String>().payload("all good").build();
    }
}
