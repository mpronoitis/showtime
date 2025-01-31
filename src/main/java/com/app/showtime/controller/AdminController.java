package com.app.showtime.controller;

import com.app.showtime.domain.Permission;
import com.app.showtime.service.AdminService;
import com.app.showtime.shared.rest.model.ApiResponse;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @RolesAllowed(Permission.ELEVATE_USER)
    @PutMapping("/admin/users/{userId}/elevate-admin")
    public ApiResponse<String> elevateUser(@PathVariable Long userId) {
        return new ApiResponse.Builder<String>().payload(adminService.elevateUser(userId)).build();
    }
}
