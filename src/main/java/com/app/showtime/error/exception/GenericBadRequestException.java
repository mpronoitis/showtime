package com.app.showtime.error.exception;

import com.app.showtime.enumeration.ErrorType;

public class GenericBadRequestException extends RuntimeException{

    private ErrorType errorType = ErrorType.IM_BAD_REQUEST;

    public GenericBadRequestException(String message) {
        super(message);
    }

    public GenericBadRequestException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public GenericBadRequestException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
