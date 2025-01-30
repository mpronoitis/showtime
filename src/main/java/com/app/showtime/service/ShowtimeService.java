package com.app.showtime.service;

import com.app.showtime.domain.*;
import com.app.showtime.dto.request.ShowtimeRequestDTO;
import com.app.showtime.dto.response.ShowtimeResponseDTO;
import com.app.showtime.dto.response.ShowtimeSeatResponseDTO;
import com.app.showtime.enumeration.SeatTypeEnum;
import com.app.showtime.error.exception.GenericBadRequestException;
import com.app.showtime.mapper.ShowtimeMapper;
import com.app.showtime.mapper.ShowtimeSeatMapper;
import com.app.showtime.repository.MovieRepository;
import com.app.showtime.repository.ShowtimeRepository;
import com.app.showtime.repository.ShowtimeSeatRepository;
import com.app.showtime.repository.TheatreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;
    private final ShowtimeRepository showtimeRepository;
    private final ShowtimeMapper showtimeMapper;
    private final ShowtimeSeatRepository showtimeSeatRepository;
    private final ShowtimeSeatMapper showtimeSeatMapper;

    @Transactional(rollbackOn = Exception.class)
    public ShowtimeResponseDTO create(ShowtimeRequestDTO showtimeRequestDTO) {

        Movie movie = movieRepository.findById(showtimeRequestDTO.getMovieId()).orElseThrow(() -> new GenericBadRequestException("Movie does not exist"));
        Theatre theatre = theatreRepository.findById(showtimeRequestDTO.getTheatreId()).orElseThrow(() -> new GenericBadRequestException("Theatre does not exist"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(showtimeRequestDTO.getStartTime(), formatter);

        //Create Showtime to save it in the repository
        Showtime showtime = new Showtime();
        //Calculate endTime for the showtime
        LocalDateTime endTime = startTime.plusMinutes(movie.getDuration());

        //Get available seats from theatre to save them for the showtime
        Set<Seat> seats =  theatre.getSeats();
        Set<ShowtimeSeat> showtimeSeats = new HashSet<>();
        for (Seat seat: seats) {
            ShowtimeSeat showtimeSeat = new ShowtimeSeat();
            showtimeSeat.setShowtime(showtime);
            showtimeSeat.setSeat(seat);
            showtimeSeat.setStatus(SeatTypeEnum.AVAILABLE);
            showtimeSeats.add(showtimeSeat);
        }

        showtime.setShowtimeSeats(showtimeSeats);
        showtime.setTheatre(theatre);
        showtime.setMovie(movie);
        showtime.setStartTime(startTime);
        showtime.setEndTime(endTime);

        Showtime savedShowtime = showtimeRepository.save(showtime);


        return showtimeMapper.toDto(savedShowtime);
    }

    public List<ShowtimeSeatResponseDTO> findAvailableSeatsForAShowtime(Long showtimeId) {

        Showtime showtime = showtimeRepository.findById(showtimeId).orElseThrow(() -> new GenericBadRequestException("Showtime does not exist"));

        List<ShowtimeSeat> showtimeSeats = showtimeSeatRepository.findByShowtimeIdAndStatusTrue(showtimeId).orElseThrow(() -> new GenericBadRequestException("There are not available seats for this showtime"));

        return showtimeSeatMapper.toDtoList(showtimeSeats);
    }
}
