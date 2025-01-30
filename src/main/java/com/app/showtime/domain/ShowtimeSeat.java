package com.app.showtime.domain;

import com.app.showtime.enumeration.SeatTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "showtime_seat")
public class ShowtimeSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Showtime showtime;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private SeatTypeEnum status;

    @OneToMany(mappedBy = "showtimeSeat", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReservationSeat> reservationSeats = new HashSet<>();


}
