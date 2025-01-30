package com.app.showtime.service;

import static java.lang.String.format;
import com.app.showtime.enumeration.ErrorType;
import com.app.showtime.error.exception.GenericBadRequestException;
import com.app.showtime.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public void findByUsername(@NotNull String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            log.error("Username {} already exists", username);
            throw new GenericBadRequestException(format("Username :%s already exists",username), ErrorType.IM_GENERIC);
        }
    }
}
