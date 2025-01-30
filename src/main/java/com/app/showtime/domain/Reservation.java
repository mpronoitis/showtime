package com.app.showtime.domain;

import com.app.showtime.enumeration.ReservationTypeEnum;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Showtime showtime;

    @ManyToOne
    private User user;

    @Column(nullable = false, name = "reservation_time")
    private LocalDateTime reservationTime;

    @Enumerated(EnumType.STRING)
    private ReservationTypeEnum status;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Set<ReservationSeat> reservationSeats = new HashSet<>();


}
