package com.app.showtime.service;

import com.app.showtime.domain.*;
import com.app.showtime.dto.RoleDTO;
import com.app.showtime.dto.UserClaimsDTO;
import com.app.showtime.error.exception.RoleNotFoundException;
import com.app.showtime.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserClaimsService {

    private final UserRoleRepository userRoleRepository;

    public UserClaimsDTO getUserClaimsDTOByUserDomain(User user) {
        UserClaimsDTO userClaimsDTO = new UserClaimsDTO();

        userClaimsDTO.setId(user.getId());
        userClaimsDTO.setUsername(user.getUsername());
        userClaimsDTO.setPassword(user.getPassword());
        userClaimsDTO.setFirstName(user.getRegistry().getFirstName());
        userClaimsDTO.setLastName(user.getRegistry().getLastName());

        //Find User Role
        UserRole userRole = userRoleRepository.findOneByUserId(user.getId()).orElseThrow(() -> new RoleNotFoundException("User does not have any role"));

        Role role;
        role = userRole.getRole();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        userClaimsDTO.setRoleDTO(roleDTO);

        Set<Permission> permissions =  role.getRolePermissions().stream().map(RolePermission::getPermission).collect(Collectors.toSet());
        userClaimsDTO.setPermissions(permissions.stream().map(Permission::getName).collect(Collectors.toSet()));

        return userClaimsDTO;
    }
}
