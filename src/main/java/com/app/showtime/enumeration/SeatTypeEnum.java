package com.app.showtime.enumeration;

import lombok.Getter;

@Getter
public enum SeatTypeEnum {

    AVAILABLE("Available"),
    RESERVED("Reserved");

    private final String status;

    SeatTypeEnum(String status) {
        this.status = status;
    }

}
