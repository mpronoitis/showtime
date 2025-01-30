package com.app.showtime.enumeration;

import lombok.Getter;

@Getter
public enum ErrorType {

    IM_GENERIC ("001","Generic error"),
    IM_BAD_REQUEST ("002","Bad request"),
    IM_BAD_REQUEST_VALIDATION ("003","Validation error"),
    IM_ROLE_NOT_FOUND("004", "Role not found"),
    IM_NOT_ROLE_TYPE_ENUM("005","Role type is incorect"),
    IM_USER_NOT_FOUND("006", "User not found"),
    IM_UNAUTHORIZED_JWT("007", "Jwt not valid"),
    IM_BAD_CREDENTIALS("008", "Bad credentials for this user"),
    IM_UNAUTHORIZED("009", "You do not have permission to access this resource");

    private final String code;
    private final String message;

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }




}
