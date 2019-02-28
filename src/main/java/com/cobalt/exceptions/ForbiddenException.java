package com.cobalt.exceptions;

import lombok.NonNull;

public class ForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 3381135154711508652L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(@NonNull final String message) {
        super(message);
    }

    public ForbiddenException(@NonNull final String message,
                              @NonNull final Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(@NonNull final Throwable cause) {
        super(cause);
    }

    protected ForbiddenException(@NonNull final String message,
                                 @NonNull final Throwable cause,
                                 @NonNull final Boolean enableSuppression,
                                 @NonNull final Boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}