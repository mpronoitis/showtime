package com.app.showtime.error.handler;


import com.app.showtime.enumeration.ErrorType;
import com.app.showtime.error.exception.GenericBadRequestException;
import com.app.showtime.error.exception.NotFoundException;
import com.app.showtime.error.exception.RoleNotFoundException;
import com.app.showtime.shared.rest.model.ApiResponse;
import jakarta.validation.constraints.Null;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GenericBadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Null> handle(GenericBadRequestException ex) {
        return new ApiResponse.Builder<Null>(ex.getErrorType().getCode(), false)
                .errorMessage(ex.getErrorType().getMessage()).build();
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Null> handle(RoleNotFoundException ex) {
        return new ApiResponse.Builder<Null>(ErrorType.IM_ROLE_NOT_FOUND.getCode(), false).errorMessage(ErrorType.IM_ROLE_NOT_FOUND.getMessage()).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Null> handle(HttpMessageNotReadableException ex) {
        return new ApiResponse.Builder<Null>(ErrorType.IM_NOT_ROLE_TYPE_ENUM.getCode(), false).errorMessage(ErrorType.IM_NOT_ROLE_TYPE_ENUM.getMessage()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Null> handle(NotFoundException ex) {
        return new ApiResponse.Builder<Null>(ErrorType.IM_GENERIC.getCode(), false).errorMessage(ErrorType.IM_GENERIC.getMessage()).build();

    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Null> handle(BadCredentialsException ex) {
        return new ApiResponse.Builder<Null>(ErrorType.IM_BAD_CREDENTIALS.getCode(), false).errorMessage(ErrorType.IM_BAD_REQUEST_VALIDATION.getMessage()).build();
    }
    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<Null> handle(InsufficientAuthenticationException ex) {
        return new ApiResponse.Builder<Null>(ErrorType.IM_UNAUTHORIZED.getCode(), false).errorMessage(ErrorType.IM_UNAUTHORIZED.getMessage()).build();
    }
}
