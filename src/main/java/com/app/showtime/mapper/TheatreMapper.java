package com.app.showtime.mapper;


import com.app.showtime.domain.Theatre;
import com.app.showtime.dto.response.TheatreResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheatreMapper extends EntityMapper<TheatreResponseDTO, Theatre> {
}
