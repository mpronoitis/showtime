package com.app.showtime.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "showtime")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne
    private Theatre theatre;

    @ManyToOne
    private Movie movie;

    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
    private Set<ShowtimeSeat> showtimeSeats = new HashSet<>();

}
