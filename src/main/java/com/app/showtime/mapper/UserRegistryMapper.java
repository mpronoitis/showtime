package com.app.showtime.mapper;


import com.app.showtime.domain.UserRegistry;
import com.app.showtime.dto.UserRegistryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRegistryMapper extends EntityMapper<UserRegistryDTO, UserRegistry> {
}
