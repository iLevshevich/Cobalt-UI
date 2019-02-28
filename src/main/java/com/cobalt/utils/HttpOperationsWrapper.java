package com.cobalt.utils;

import com.cobalt.exceptions.BadRequestException;
import com.cobalt.exceptions.ForbiddenException;
import lombok.NonNull;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface HttpOperationsWrapper extends HttpStatusMessage {
    Logger getLogger();

    default String operationsWrapper(@NonNull final Supplier<String> operation,
                                     @NonNull final Consumer<String> responseCheck,
                                     @NonNull final Function<String, String> responseSuccess,
                                     @NonNull final Function<String, String> responseFailed,
                                     @NonNull final Supplier<String> logMessage) {
        try {
            final String result = operation.get();
            {
                check(result, responseCheck);
            }
            return success(result, responseSuccess);
        } catch (final BadRequestException ex) {
            return failed(responseFailed, ex, logMessage, this::getHttpStatusBadRequestMessage);
        } catch (final ForbiddenException ex) {
            return failed(responseFailed, ex, logMessage, this::getHttpStatusForbiddenMessage);
        } catch (final Exception ex) {
            return failed(responseFailed, ex, logMessage, this::getHttpStatusInternalServerErrorMessage);
        }
    }

    default String operationsWrapper(@NonNull final Supplier<String> operation,
                                     @NonNull final Function<String, String> responseSuccess,
                                     @NonNull final Function<String, String> responseFailed,
                                     @NonNull final Supplier<String> logMessage) {
        try {
            final String result = operation.get();
            return success(result, responseSuccess);
        } catch (final BadRequestException ex) {
            return failed(responseFailed, ex, logMessage, this::getHttpStatusBadRequestMessage);
        } catch (final ForbiddenException ex) {
            return failed(responseFailed, ex, logMessage, this::getHttpStatusForbiddenMessage);
        } catch (final Exception ex) {
            return failed(responseFailed, ex, logMessage, this::getHttpStatusInternalServerErrorMessage);
        }
    }

    default void check(@NonNull final String result,
                       @NonNull final Consumer<String> responseCheck) {
        responseCheck.accept(result);
    }

    default String success(@NonNull final String result,
                           @NonNull final Function<String, String> responseSuccess) {
        return responseSuccess.apply(result);
    }

    default <T extends Throwable> String failed(@NonNull final Function<String, String> responseFailed,
                                                @NonNull final T exception,
                                                @NonNull final Supplier<String> logMessage,
                                                @NonNull final Supplier<String> statusMessage) {
        final Logger logger = getLogger();
        Objects.requireNonNull(logger);

        final String errorMessage = logMessage.get();
        logger.error(errorMessage, exception);

        final String result = statusMessage.get();
        return responseFailed.apply(result);
    }
}
