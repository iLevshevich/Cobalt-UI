package com.cobalt.exceptions;

import lombok.NonNull;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 3381135154711508652L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(@NonNull final String message) {
        super(message);
    }

    public BadRequestException(@NonNull final String message,
                               @NonNull final Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(@NonNull final Throwable cause) {
        super(cause);
    }

    protected BadRequestException(@NonNull final String message,
                                  @NonNull final Throwable cause,
                                  @NonNull final Boolean enableSuppression,
                                  @NonNull final Boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}