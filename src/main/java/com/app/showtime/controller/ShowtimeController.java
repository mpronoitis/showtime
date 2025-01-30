package com.app.showtime.controller;

import com.app.showtime.domain.Permission;
import com.app.showtime.dto.request.ShowtimeRequestDTO;
import com.app.showtime.dto.response.ShowtimeResponseDTO;
import com.app.showtime.dto.response.ShowtimeSeatResponseDTO;
import com.app.showtime.service.ShowtimeService;
import com.app.showtime.shared.rest.model.ApiResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService showtimeService;

    @RolesAllowed(Permission.ADD_SHOWTIME)
    @PostMapping("/admin/showtimes/create")
    public ApiResponse<ShowtimeResponseDTO> createShowtime(@Valid @RequestBody ShowtimeRequestDTO showtimeRequestDTO) {
        return new ApiResponse.Builder<ShowtimeResponseDTO>().payload(showtimeService.create(showtimeRequestDTO)).build();
    }

    //User can find available seats for a showtime
    @GetMapping("/showtimes/{showtimeId}/available-seats")
    public ApiResponse<List<ShowtimeSeatResponseDTO>> findAvailableSeatsForAShowtime(@PathVariable("showtimeId") Long showtimeId) {
        return new ApiResponse.Builder<List<ShowtimeSeatResponseDTO>>().payload(showtimeService.findAvailableSeatsForAShowtime(showtimeId)).build();
    }
}
