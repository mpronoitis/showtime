package com.app.showtime.service;

import com.app.showtime.domain.Role;
import com.app.showtime.domain.User;
import com.app.showtime.domain.UserRole;
import com.app.showtime.enumeration.ErrorType;
import com.app.showtime.enumeration.RoleTypeEnum;
import com.app.showtime.error.exception.GenericBadRequestException;
import com.app.showtime.error.exception.RoleNotFoundException;
import com.app.showtime.repository.RoleRepository;
import com.app.showtime.repository.UserRepository;
import com.app.showtime.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    @Transactional(rollbackOn = Exception.class)
    public String elevateUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new GenericBadRequestException("", ErrorType.IM_USER_NOT_FOUND));
        UserRole userRole = new UserRole();
        //Find the admin role
        Role role = roleRepository.findByName(RoleTypeEnum.ADMIN.getText()).orElseThrow(() -> new RoleNotFoundException("Role does not found"));
        userRole.setRole(role);
        userRole.setUser(user);

        user.getUserRoles().add(userRole);

        userRepository.save(user);

        return "User Elevated to Admin";
    }
}
