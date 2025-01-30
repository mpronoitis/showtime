package com.app.showtime.controller;

import com.app.showtime.dto.request.ReservationRequestDTO;
import com.app.showtime.dto.response.ReservationResponseDTO;
import com.app.showtime.service.ReservationService;
import com.app.showtime.shared.rest.model.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("")
    public ApiResponse<List<ReservationResponseDTO>> findReservations() {
        return new ApiResponse.Builder<List<ReservationResponseDTO>>().payload(reservationService.findReservationsByUser()).build();
    }

    @PostMapping("/create")
    public ApiResponse<ReservationResponseDTO> createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO) {
        return new ApiResponse.Builder<ReservationResponseDTO>().payload(reservationService.create(reservationRequestDTO)).build();
    }



    @PostMapping("/{reservationId}/cancel")
    public ApiResponse<String> cancelUpcomingReservation(@PathVariable Long reservationId, @RequestParam Long userId) {
        return new ApiResponse.Builder<String>().payload(reservationService.cancelUpComingReservation(reservationId,userId)).build();
    }
}
