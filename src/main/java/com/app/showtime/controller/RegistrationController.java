package com.app.showtime.controller;

import com.app.showtime.dto.request.RegisteringUserRequestDTO;
import com.app.showtime.dto.response.RegisteringUserResponseDTO;
import com.app.showtime.service.RegistrationService;
import com.app.showtime.shared.rest.model.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/public/registration/sign-up")
    public ApiResponse<RegisteringUserResponseDTO> signUp(@Valid @RequestBody RegisteringUserRequestDTO registeringUserRequestDTO) {
        return new ApiResponse.Builder<RegisteringUserResponseDTO>().payload(registrationService.registerUser(registeringUserRequestDTO)).build();
    }
}
