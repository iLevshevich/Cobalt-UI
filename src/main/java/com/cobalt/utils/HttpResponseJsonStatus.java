package com.cobalt.utils;

import com.cobalt.helpers.JsonHelper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;

import java.util.Objects;

public interface HttpResponseJsonStatus {
    String key = "status";
    String valueSuccess = "SUCCESS";
    String valueFailed = "FAILED";
    String valueUnknown = "UNKNOWN";

    default String addStatus(@NonNull final String response,
                             @NonNull final String value) {
        final JsonHelper helper = getJsonHelper();
        Objects.requireNonNull(helper);

        final JsonNode result = helper.toJson(response);
        Objects.requireNonNull(result);
        {
            helper.put(result, key, value);
        }
        return helper.toString(result);
    }

    default String removeStatus(@NonNull final String response,
                                @NonNull final String key) {
        final JsonHelper helper = getJsonHelper();
        Objects.requireNonNull(helper);

        final JsonNode result = helper.toJson(response);
        Objects.requireNonNull(result);
        {
            helper.remove(result, key);
        }
        return helper.toString(result);
    }

    default String getStatus(@NonNull final String response,
                             @NonNull final String key) {
        final JsonHelper helper = getJsonHelper();
        Objects.requireNonNull(helper);

        final JsonNode responseJson = helper.toJson(response);
        Objects.requireNonNull(responseJson);

        final JsonNode result = helper.get(responseJson, key);
        if (Objects.isNull(result)) {
            return valueUnknown;
        }

        return helper.asText(result);
    }

    default String addSuccessJsonStatus(@NonNull final String response) {
        return addStatus(response, valueSuccess);
    }

    default String addFailedJsonStatus(@NonNull final String response) {
        return addStatus(response, valueFailed);
    }

    default String removeJsonStatus(@NonNull final String response) {
        return removeStatus(response, key);
    }

    default void checkSuccessJsonStatus(@NonNull final String response) {
        if (!isSuccessJsonStatus(response)) {
            throw new RuntimeException("Invalid operation status");
        }
    }

    default Boolean isSuccessJsonStatus(@NonNull final String response) {
        return getStatus(response, key).equalsIgnoreCase(valueSuccess);
    }

    default Boolean isFailedJsonStatus(@NonNull final String response) {
        return getStatus(response, key).equalsIgnoreCase(valueFailed);
    }

    default Boolean isUnknownJsonStatus(@NonNull final String response) {
        return getStatus(response, key).equalsIgnoreCase(valueUnknown);
    }

    JsonHelper getJsonHelper();
}
