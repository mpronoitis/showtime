package com.app.showtime.shared.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private final String errorCode;
    private final String errorMessage;
    private final boolean success;
    private final T payload;

    private ApiResponse(Builder<T> builder) {
        this.errorCode = builder.errorCode;;
        this.errorMessage = builder.errorMessage;
        this.success = builder.success;
        this.payload = builder.payload;

    }

    public static class Builder<T> {
        private final String errorCode;
        private String errorMessage;
        private final boolean success;
        private  T payload;

        public Builder() {
            this.errorCode = "";
            this.success = true;
        }

        public Builder(String errorCode, boolean success) {
            this.errorCode = errorCode;
            this.success = success;
        }

        public Builder<T> errorMessage(String errorMessage) {
           this.errorMessage = errorMessage;
           return this;
        }

        public Builder<T> payload(T payload) {
            this.payload = payload;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<>(this);
        }
    }
}
