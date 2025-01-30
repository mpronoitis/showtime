package com.app.showtime.mapper;

public interface EntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);
}
