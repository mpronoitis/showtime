package com.app.showtime.service;

import com.app.showtime.domain.*;
import com.app.showtime.dto.UserClaimsDTO;
import com.app.showtime.dto.request.ReservationRequestDTO;
import com.app.showtime.dto.response.ReservationResponseDTO;
import com.app.showtime.enumeration.ErrorType;
import com.app.showtime.enumeration.ReservationTypeEnum;
import com.app.showtime.enumeration.SeatTypeEnum;
import com.app.showtime.error.exception.GenericBadRequestException;
import com.app.showtime.mapper.ReservationMapper;
import com.app.showtime.repository.ReservationRepository;
import com.app.showtime.repository.ShowtimeRepository;
import com.app.showtime.repository.ShowtimeSeatRepository;
import com.app.showtime.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ShowtimeSeatRepository showtimeSeatRepository;
    private final ShowtimeRepository showtimeRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Transactional(rollbackOn = Exception.class)
    public ReservationResponseDTO create(ReservationRequestDTO reservationRequestDTO) {

        Showtime showtime = showtimeRepository.findById(reservationRequestDTO.getShowtimeId()).orElseThrow(() -> new GenericBadRequestException("Not found", ErrorType.IM_GENERIC));


        List<ShowtimeSeat> showtimeSeats = showtimeSeatRepository.findShowtimeSeatsByShowtimeIdAndSeatNumber(reservationRequestDTO.getShowtimeId(), reservationRequestDTO.getSeatNames());
        if (showtimeSeats.isEmpty() || showtimeSeats.size() != reservationRequestDTO.getSeatNames().size()) {
            throw new GenericBadRequestException("Some seats can not be reserved for the showtime");
        }
        //Find User that want to create the reservation
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserClaimsDTO userClaimsDTO = (UserClaimsDTO) authentication.getPrincipal();
        User user = userRepository.findByUsername(userClaimsDTO.getUsername()).orElseThrow(() -> new GenericBadRequestException("User not found", ErrorType.IM_USER_NOT_FOUND));

        //Create the reservation
        Reservation reservation = new Reservation();
        reservation.setReservationTime(LocalDateTime.now());
        reservation.setUser(user);
        reservation.setStatus(ReservationTypeEnum.CONFIRMED);
        reservation.setShowtime(showtime);

        //Mark Selected Seats as reserved
        List<ShowtimeSeat> reservedShowtimeSeats = new ArrayList<>();
        Set<ReservationSeat> reservationSeats = new HashSet<>();
        for (ShowtimeSeat showtimeSeat: showtimeSeats) {
            ReservationSeat reservationSeat = new ReservationSeat();
            showtimeSeat.setStatus(SeatTypeEnum.RESERVED);
            reservedShowtimeSeats.add(showtimeSeat);
           reservationSeat.setShowtimeSeat(showtimeSeat);
           reservationSeat.setReservation(reservation);
           reservationSeats.add(reservationSeat);
        }
        reservation.setReservationSeats(reservationSeats);


        showtimeSeatRepository.saveAll(reservedShowtimeSeats);
        Reservation savedReservation =  reservationRepository.save(reservation);

        return reservationMapper.toDto(savedReservation);

    }

    public List<ReservationResponseDTO> findReservationsByUser() {

        //find the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserClaimsDTO userClaimsDTO = (UserClaimsDTO) authentication.getPrincipal();
        User user = userRepository.findById(userClaimsDTO.getId()).orElseThrow(() -> new GenericBadRequestException("",ErrorType.IM_USER_NOT_FOUND));

        List<Reservation> reservations = reservationRepository.findReservationByUserId(userClaimsDTO.getId());

        return reservationMapper.toDtoList(reservations);
    }


    @Transactional(rollbackOn = Exception.class)
    public String cancelUpComingReservation(Long reservationId, Long userId) {

        //Check if reservation exists
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new GenericBadRequestException("Reservation does not exist"));

        //Check if the reservation belongs to the user
        if (!reservation.getUser().getId().equals(userId)) {
            throw new GenericBadRequestException("You can not cancel a reservation that belongs to another user");
        }

        //User can cancel only upcoming showtimes
        if (reservation.getShowtime().getStartTime().isBefore(LocalDateTime.now())) {
            throw new GenericBadRequestException("Can not cancel past reservations");
        }

        reservation.setStatus(ReservationTypeEnum.CANCELED);

        //Release seats associated with this reservation
        Set<ReservationSeat> reservationSeats = new HashSet<>();
        reservationSeats = reservation.getReservationSeats();
        for (ReservationSeat reservationSeat: reservationSeats) {
            reservationSeat.getShowtimeSeat().setStatus(SeatTypeEnum.AVAILABLE);
        }

        reservation.setReservationSeats(reservationSeats);
        reservationRepository.save(reservation);

        return "Reservation cancelled";
    }
}
