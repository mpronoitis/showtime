package com.app.showtime.service;

import com.app.showtime.domain.Role;
import com.app.showtime.domain.User;
import com.app.showtime.domain.UserRegistry;
import com.app.showtime.domain.UserRole;
import com.app.showtime.dto.request.RegisteringUserRequestDTO;
import com.app.showtime.dto.response.RegisteringUserResponseDTO;
import com.app.showtime.enumeration.RoleTypeEnum;
import com.app.showtime.error.exception.RoleNotFoundException;
import com.app.showtime.mapper.UserRegisteredMapper;
import com.app.showtime.repository.RoleRepository;
import com.app.showtime.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRegisteredMapper userRegisteredMapper;


    @Transactional(rollbackOn = Exception.class)
    public RegisteringUserResponseDTO registerUser(RegisteringUserRequestDTO registeringUserRequestDTO) {
       userService.findByUsername(registeringUserRequestDTO.getUsername()); // if exists a GenericBadRequestException will be thrown

        //Find Role to give to the user
        Role role = roleRepository.findByName(registeringUserRequestDTO.getRoleCode() != null ? registeringUserRequestDTO.getRoleCode().getText() : RoleTypeEnum.USER.getText()).orElseThrow(() -> new RoleNotFoundException(format("Role with name :%s not found",RoleTypeEnum.USER.getText())));


        User user = new User();

        //Create and fill the userRoles;
        Set<UserRole> userRoles= new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoles.add(userRole);

        //Create and fill the UserRegistry
        UserRegistry userRegistry = new UserRegistry();
        userRegistry.setUser(user);
        userRegistry.setFirstName(registeringUserRequestDTO.getFirstName());
        userRegistry.setLastName(registeringUserRequestDTO.getLastName());

        //Create and fill User

        user.setUsername(registeringUserRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registeringUserRequestDTO.getPassword()));
        user.setUserRoles(userRoles);
        user.setRegistry(userRegistry);

        User savedUser = userRepository.save(user);

        //Create the Response DTO
        RegisteringUserResponseDTO registeringUserResponseDTO = new RegisteringUserResponseDTO();
        registeringUserResponseDTO.setUserRegisteredDTO(userRegisteredMapper.toDto(savedUser));

        return registeringUserResponseDTO;
    }
}
