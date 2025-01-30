package com.app.showtime.controller;

import com.app.showtime.domain.Theatre;
import com.app.showtime.service.TheatreService;
import com.app.showtime.shared.rest.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @GetMapping("/admin/theatres")
    public ApiResponse<List<Theatre>> findAll() {
        return new ApiResponse.Builder<List<Theatre>>().payload(theatreService.findAll()).build();
    }
}
