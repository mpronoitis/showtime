package com.app.showtime.controller;

import com.app.showtime.dto.request.AuthRequestDTO;
import com.app.showtime.service.AuthService;
import com.app.showtime.shared.rest.model.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/public/auth/sign-in")
    public ApiResponse<String> login(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        return new ApiResponse.Builder<String>().payload(authService.login(authRequestDTO)).build();
    }
}
