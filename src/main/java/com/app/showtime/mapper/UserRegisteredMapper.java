package com.app.showtime.mapper;

import com.app.showtime.domain.User;
import com.app.showtime.dto.response.UserRegisteredDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserRegistryMapper.class)
public interface UserRegisteredMapper extends EntityMapper<UserRegisteredDTO, User> {
}
