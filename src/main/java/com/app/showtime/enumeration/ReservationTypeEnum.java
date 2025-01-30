package com.app.showtime.enumeration;

import lombok.Getter;

@Getter
public enum ReservationTypeEnum {

    CONFIRMED("Confirmed"),
    CANCELED("Canceled");

    private final String status;

    ReservationTypeEnum(String status) {
        this.status = status;
    }
}
