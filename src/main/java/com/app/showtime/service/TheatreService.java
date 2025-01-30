package com.app.showtime.service;

import com.app.showtime.domain.Theatre;
import com.app.showtime.repository.TheatreRepository;
import com.app.showtime.shared.rest.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreService {

    private final TheatreRepository theatreRepository;
    public List<Theatre> findAll() {

        return theatreRepository.findAll();
    }
}
