package com.cobalt.utils;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface HttpStatusMessage {
    JsonFactory JSON_FACTORY = new JsonFactory();

    // 1xx Informational

    /**
     * {@code 100 Continue}.
     */
    default String getHttpStatusContinueMessage() {
        return getHttpStatusMessage(HttpStatus.CONTINUE);
    }

    /**
     * {@code 101 Switching Protocols}.
     */
    default String getHttpStatusSwitchingProtocolsMessage() {
        return getHttpStatusMessage(HttpStatus.SWITCHING_PROTOCOLS);
    }

    /**
     * {@code 102 Processing}.
     */
    default String getHttpStatusProcessingMessage() {
        return getHttpStatusMessage(HttpStatus.PROCESSING);
    }

    /**
     * {@code 103 Checkpoint}.
     */
    default String getHttpStatusCheckpointMessage() {
        return getHttpStatusMessage(HttpStatus.CHECKPOINT);
    }

    // 2xx Success

    /**
     * {@code 200 OK}.
     */
    default String getHttpStatusOkMessage() {
        return getHttpStatusMessage(HttpStatus.OK);
    }

    /**
     * {@code 201 Created}.
     */
    default String getHttpStatusCreatedMessage() {
        return getHttpStatusMessage(HttpStatus.CREATED);
    }

    /**
     * {@code 202 Accepted}.
     */
    default String getHttpStatusAcceptedMessage() {
        return getHttpStatusMessage(HttpStatus.ACCEPTED);
    }

    /**
     * {@code 203 Non-Authoritative Information}.
     */
    default String getHttpStatusNonAuthoritativeInformationMessage() {
        return getHttpStatusMessage(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    /**
     * {@code 204 No Content}.
     */
    default String getHttpStatusNoContentMessage() {
        return getHttpStatusMessage(HttpStatus.NO_CONTENT);
    }

    /**
     * {@code 205 Reset Content}.
     */
    default String getHttpStatusResetContentMessage() {
        return getHttpStatusMessage(HttpStatus.RESET_CONTENT);
    }

    /**
     * {@code 206 Partial Content}.
     */
    default String getHttpStatusPartialContentMessage() {
        return getHttpStatusMessage(HttpStatus.PARTIAL_CONTENT);
    }

    /**
     * {@code 207 Multi-Status}.
     */
    default String getHttpStatusMultiStatusMessage() {
        return getHttpStatusMessage(HttpStatus.MULTI_STATUS);
    }

    /**
     * {@code 208 Already Reported}.
     */
    default String getHttpStatusAlreadyReportedMessage() {
        return getHttpStatusMessage(HttpStatus.ALREADY_REPORTED);
    }

    /**
     * {@code 226 IM Used}.
     */
    default String getHttpStatusIMUsedMessage() {
        return getHttpStatusMessage(HttpStatus.IM_USED);
    }

    // 3xx Redirection

    /**
     * {@code 300 Multiple Choices}.
     */
    default String getHttpStatusMultipleChoicesMessage() {
        return getHttpStatusMessage(HttpStatus.MULTIPLE_CHOICES);
    }

    /**
     * {@code 301 Moved Permanently}.
     */
    default String getHttpStatusMovedPermanentlyMessage() {
        return getHttpStatusMessage(HttpStatus.MOVED_PERMANENTLY);
    }

    /**
     * {@code 302 Found}.
     */
    default String getHttpStatusFoundMessage() {
        return getHttpStatusMessage(HttpStatus.FOUND);
    }

    /**
     * {@code 303 See Other}.
     */
    default String getHttpStatusSeeOtherMessage() {
        return getHttpStatusMessage(HttpStatus.SEE_OTHER);
    }

    /**
     * {@code 304 Not Modified}.
     */
    default String getHttpStatusNotModifiedMessage() {
        return getHttpStatusMessage(HttpStatus.NOT_MODIFIED);
    }

    /**
     * {@code 307 Temporary Redirect}.
     */
    default String getHttpStatusTemporaryRedirectMessage() {
        return getHttpStatusMessage(HttpStatus.TEMPORARY_REDIRECT);
    }

    /**
     * {@code 308 Permanent Redirect}.
     */
    default String getHttpStatusPermanentRedirectMessage() {
        return getHttpStatusMessage(HttpStatus.PERMANENT_REDIRECT);
    }

    // --- 4xx Client Error ---

    /**
     * {@code 400 Bad Request}.
     */
    default String getHttpStatusBadRequestMessage() {
        return getHttpStatusMessage(HttpStatus.BAD_REQUEST);
    }

    /**
     * {@code 401 Unauthorized}.
     */
    default String getHttpStatusUnauthorizedMessage() {
        return getHttpStatusMessage(HttpStatus.UNAUTHORIZED);
    }

    /**
     * {@code 402 Payment Required}.
     */
    default String getHttpStatusPaymentRequiredMessage() {
        return getHttpStatusMessage(HttpStatus.PAYMENT_REQUIRED);
    }

    /**
     * {@code 403 Forbidden}.
     */
    default String getHttpStatusForbiddenMessage() {
        return getHttpStatusMessage(HttpStatus.FORBIDDEN);
    }

    /**
     * {@code 404 Not Found}.
     */
    default String getHttpStatusNotFoundMessage() {
        return getHttpStatusMessage(HttpStatus.NOT_FOUND);
    }

    /**
     * {@code 405 Method Not Allowed}.
     */
    default String getHttpStatusMethodNotAllowedMessage() {
        return getHttpStatusMessage(HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * {@code 406 Not Acceptable}.
     */
    default String getHttpStatusNotAcceptableMessage() {
        return getHttpStatusMessage(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * {@code 407 Proxy Authentication Required}.
     */
    default String getHttpStatusProxyAuthenticationRequiredMessage() {
        return getHttpStatusMessage(HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
    }

    /**
     * {@code 408 Request Timeout}.
     */
    default String getHttpStatusRequestTimeoutMessage() {
        return getHttpStatusMessage(HttpStatus.REQUEST_TIMEOUT);
    }

    /**
     * {@code 409 Conflict}.
     */
    default String getHttpStatusConflictMessage() {
        return getHttpStatusMessage(HttpStatus.CONFLICT);
    }

    /**
     * {@code 410 Gone}.
     */
    default String getHttpStatusGoneMessage() {
        return getHttpStatusMessage(HttpStatus.GONE);
    }

    /**
     * {@code 411 Length Required}.
     */
    default String getHttpStatusLengthRequiredMessage() {
        return getHttpStatusMessage(HttpStatus.LENGTH_REQUIRED);
    }

    /**
     * {@code 412 Precondition failed}.
     */
    default String getHttpStatusPreconditionFailedMessage() {
        return getHttpStatusMessage(HttpStatus.PRECONDITION_FAILED);
    }

    /**
     * {@code 413 Payload Too Large}.
     */
    default String getHttpStatusPayloadTooLargeMessage() {
        return getHttpStatusMessage(HttpStatus.PAYLOAD_TOO_LARGE);
    }

    /**
     * {@code 414 URI Too Long}.
     */
    default String getHttpStatusURITooLongMessage() {
        return getHttpStatusMessage(HttpStatus.URI_TOO_LONG);
    }

    /**
     * {@code 415 Unsupported Media Type}.
     */
    default String getHttpStatusUnsupportedMediaTypeMessage() {
        return getHttpStatusMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * {@code 416 Requested Range Not Satisfiable}.
     */
    default String getHttpStatusRequestedRangeNotSatisfiableMessage() {
        return getHttpStatusMessage(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
    }

    /**
     * {@code 417 Expectation Failed}.
     */
    default String getHttpStatusExpectationFailedMessage() {
        return getHttpStatusMessage(HttpStatus.EXPECTATION_FAILED);
    }

    /**
     * {@code 418 I'm a teapot}.
     */
    default String getHttpStatusImTeapotMessage() {
        return getHttpStatusMessage(HttpStatus.I_AM_A_TEAPOT);
    }

    /**
     * {@code 422 Unprocessable Entity}.
     */
    default String getHttpStatusUnprocessableEntityMessage() {
        return getHttpStatusMessage(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * {@code 423 Locked}.
     */
    default String getHttpStatusLockedMessage() {
        return getHttpStatusMessage(HttpStatus.LOCKED);
    }

    /**
     * {@code 424 Failed Dependency}.
     */
    default String getHttpStatusFailedDependencyMessage() {
        return getHttpStatusMessage(HttpStatus.FAILED_DEPENDENCY);
    }

    /**
     * {@code 426 Upgrade Required}.
     */
    default String getHttpStatusUpgradeRequiredMessage() {
        return getHttpStatusMessage(HttpStatus.UPGRADE_REQUIRED);
    }

    /**
     * {@code 428 Precondition Required}.
     */
    default String getHttpStatusPreconditionRequiredMessage() {
        return getHttpStatusMessage(HttpStatus.PRECONDITION_REQUIRED);
    }

    /**
     * {@code 429 Too Many Requests}.
     */
    default String getHttpStatusTooManyRequestsMessage() {
        return getHttpStatusMessage(HttpStatus.TOO_MANY_REQUESTS);
    }

    /**
     * {@code 431 Request Header Fields Too Large}.
     */
    default String getHttpStatusRequestHeaderFieldsTooLargeMessage() {
        return getHttpStatusMessage(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE);
    }

    // --- 5xx Server Error ---

    /**
     * {@code 500 Internal Server Error}.
     */
    default String getHttpStatusInternalServerErrorMessage() {
        return getHttpStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * {@code 501 Not Implemented}.
     */
    default String getHttpStatusNotImplementedMessage() {
        return getHttpStatusMessage(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * {@code 502 Bad Gateway}.
     */
    default String getHttpStatusBadGatewayMessage() {
        return getHttpStatusMessage(HttpStatus.BAD_GATEWAY);
    }

    /**
     * {@code 503 Service Unavailable}.
     */
    default String getHttpStatusServiceUnavailableMessage() {
        return getHttpStatusMessage(HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * {@code 504 Gateway Timeout}.
     */
    default String getHttpStatusGatewayTimeoutMessage() {
        return getHttpStatusMessage(HttpStatus.GATEWAY_TIMEOUT);
    }

    /**
     * {@code 505 HTTP Version Not Supported}.
     */
    default String getHttpStatusHttpVersionNotSupportedMessage() {
        return getHttpStatusMessage(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    }

    /**
     * {@code 506 Variant Also Negotiates}
     */
    default String getHttpStatusVariantAlsoNegotiatesMessage() {
        return getHttpStatusMessage(HttpStatus.VARIANT_ALSO_NEGOTIATES);
    }

    /**
     * {@code 507 Insufficient Storage}
     */
    default String getHttpStatusInsufficientStorageMessage() {
        return getHttpStatusMessage(HttpStatus.INSUFFICIENT_STORAGE);
    }

    /**
     * {@code 508 Loop Detected}
     */
    default String getHttpStatusLoopDetectedMessage() {
        return getHttpStatusMessage(HttpStatus.LOOP_DETECTED);
    }

    /**
     * {@code 509 Bandwidth Limit Exceeded}
     */
    default String getHttpStatusBandwidthLimitExceededMessage() {
        return getHttpStatusMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }

    /**
     * {@code 510 Not Extended}
     */
    default String getHttpStatusNotExtendedMessage() {
        return getHttpStatusMessage(HttpStatus.NOT_EXTENDED);
    }

    /**
     * {@code 511 Network Authentication Required}.
     */
    default String getHttpStatusNetworkAuthenticationRequiredMessage() {
        return getHttpStatusMessage(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @SneakyThrows({IOException.class})
    default String getHttpStatusMessage(@NonNull final HttpStatus status) {
        @Cleanup final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        @Cleanup final JsonGenerator generator = JSON_FACTORY.createGenerator(stream, JsonEncoding.UTF8);
        {
            generator.writeStartObject();
            generator.writeStringField("status", "failed");
            generator.writeNumberField("code", status.value());
            generator.writeStringField("reason", status.getReasonPhrase());
            generator.writeEndObject();
            generator.flush();
        }
        final String result = new String(stream.toByteArray(), "UTF-8");
        return result;
    }
}
