package com.app.showtime.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true, name = "seat_number")
    private String seatNumber;

    @NotNull
    @ManyToOne
    private Theatre theatre;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    private Set<ShowtimeSeat> showtimeSeats;

    public Seat(String seatNumber, Theatre theatre) {
        this.seatNumber = seatNumber;
        this.theatre = theatre;
    }
}
