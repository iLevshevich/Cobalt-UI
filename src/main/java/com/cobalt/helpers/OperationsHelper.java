package com.cobalt.helpers;

import com.cobalt.annotations.IsAvailable;
import com.cobalt.helpers.xml.entities.Grant;
import com.cobalt.helpers.xml.entities.Grants;
import com.cobalt.helpers.xml.entities.User;
import com.cobalt.helpers.xml.entities.Users;
import com.cobalt.model.RequestEntity;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class OperationsHelper {
    private static final String ALLOW_OPERATION = "allow";

    private static final JsonPointer DISPATCHERS_POINTER = JsonPointer.compile("/dispatchers");
    private static final JsonPointer INPUT_POINTER = JsonPointer.compile("/input");
    private static final JsonPointer OUTPUT_POINTER = JsonPointer.compile("/output");

    private final OperationSignatureHelper signatureHelper;
    private final RequestHelper requestHelper;
    private final JsonHelper jsonHelper;
    private final UserHelper userHelper;
    private final ConverterHelper converterHelper;
    private final ShutdownHelper shutdownHelper;

    @Autowired
    public OperationsHelper(@NonNull final OperationSignatureHelper signatureHelper,
                            @NonNull final RequestHelper requestHelper,
                            @NonNull final JsonHelper jsonHelper,
                            @NonNull final UserHelper userHelper,
                            @NonNull final ConverterHelper converterHelper,
                            @NonNull final ShutdownHelper shutdownHelper) {
        this.signatureHelper = signatureHelper;
        this.requestHelper = requestHelper;
        this.jsonHelper = jsonHelper;
        this.userHelper = userHelper;
        this.converterHelper = converterHelper;
        this.shutdownHelper = shutdownHelper;
    }

    public String getGrants() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Objects.requireNonNull(auth);

        final String name = auth.getName();
        Objects.requireNonNull(name);

        final JsonNode result = jsonHelper.getInstance();
        Objects.requireNonNull(result);
        {
            final Users users = userHelper.getUsers();
            Objects.requireNonNull(users);

            final List<User> userList = users.getUser();
            Objects.requireNonNull(userList);

            final User currentUser = userList.stream()
                    .filter(user -> user.getName().equals(name))
                    .findFirst()
                    .orElse(null);
            Objects.requireNonNull(currentUser);

            final ArrayNode userGrants = jsonHelper.getArrayInstance();
            Objects.requireNonNull(userGrants);
            {
                final Grants grants = currentUser.getGrants();
                Objects.requireNonNull(grants);

                final List<Grant> grantList = grants.getGrant();
                Objects.requireNonNull(grantList);

                grantList.stream()
                        .filter(grant -> grant.getAvailable().equals(ALLOW_OPERATION))
                        .forEach(grant -> jsonHelper.put(userGrants, grant.getOperation()));
            }
            jsonHelper.put(result, "grants", userGrants);
        }
        return jsonHelper.toString(result);
    }

    public String getTypes() {
        return operationWrapper(signatureHelper.getTypesOperation());
    }

    public String getStatuses() {
        return operationWrapper(signatureHelper.getStatusesOperation(),
                this::ignore,
                (data) -> {
                    final JsonNode result = jsonHelper.toJson(data);
                    Objects.requireNonNull(result);
                    {
                        result.at(DISPATCHERS_POINTER)
                                .forEach(dispatcher -> {
                                    final JsonNode input = dispatcher.at(INPUT_POINTER);
                                    Objects.requireNonNull(input);
                                    {
                                        jsonHelper.removeIsExist(input, "user");
                                        jsonHelper.removeIsExist(input, "password");
                                    }

                                    final JsonNode output = dispatcher.at(OUTPUT_POINTER);
                                    Objects.requireNonNull(output);
                                    {
                                        jsonHelper.removeIsExist(output, "user");
                                        jsonHelper.removeIsExist(output, "password");
                                    }

                                    jsonHelper.removeIsExist(dispatcher, "error");
                                });
                    }
                    return jsonHelper.toString(result);
                });
    }

    @IsAvailable("CleanLastErrors")
    public String cleanLastErrors() {
        return operationWrapper(signatureHelper.getCleanLastErrorsOperation());
    }

    @IsAvailable("SaveConfiguration")
    public String saveConfiguration() {
        return operationWrapper(signatureHelper.getSaveConfigurationOperation());
    }

    @IsAvailable("StartDispatchers")
    public String startAll() {
        return operationWrapper(signatureHelper.getStartAllOperation());
    }

    @IsAvailable("AbortDispatchers")
    public String abortAll() {
        return operationWrapper(signatureHelper.getAbortAllOperation());
    }

    @IsAvailable("ResetDispatchers")
    public String resetAll() {
        return operationWrapper(signatureHelper.getResetAllOperation());
    }

    @IsAvailable("StopDispatchers")
    public String stopAll() {
        return operationWrapper(signatureHelper.getStopAllOperation());
    }

    @IsAvailable("AddDispatcher")
    public String add(@NonNull final Map<String, Object> requestMap) {
        return operationWrapper(signatureHelper.getAddOperation(),
                (request) -> {
                    final RequestEntity entity = converterHelper.convert(requestMap);
                    final JsonNode signature = jsonHelper.toJson(request);

                    return converterHelper.convert(entity, signature);
                },
                this::ignore);
    }

    @IsAvailable("RemoveDispatcher")
    public String remove(@NonNull final String id) {
        return operationWrapper(signatureHelper.getRemoveOperation(),
                (request) -> {
                    final JsonNode result = jsonHelper.toJson(request);
                    {
                        jsonHelper.put(result, "id", id);
                    }
                    return jsonHelper.toString(result);
                },
                this::ignore);
    }

    @IsAvailable("StartDispatcher")
    public String start(@NonNull final String id) {
        return operationWrapper(signatureHelper.getStartOperation(),
                (request) -> {
                    final JsonNode result = jsonHelper.toJson(request);
                    {
                        jsonHelper.put(result, "id", id);
                    }
                    return jsonHelper.toString(result);
                },
                this::ignore);
    }

    @IsAvailable("AbortDispatcher")
    public String abort(@NonNull final String id) {
        return operationWrapper(signatureHelper.getAbortOperation(),
                (request) -> {
                    final JsonNode result = jsonHelper.toJson(request);
                    {
                        jsonHelper.put(result, "id", id);
                    }
                    return jsonHelper.toString(result);
                },
                this::ignore);
    }

    @IsAvailable("ResetDispatcher")
    public String reset(@NonNull final String id) {
        return operationWrapper(signatureHelper.getResetOperation(),
                (request) -> {
                    final JsonNode result = jsonHelper.toJson(request);
                    {
                        jsonHelper.put(result, "id", id);
                    }
                    return jsonHelper.toString(result);
                },
                this::ignore);
    }

    @IsAvailable("StopDispatcher")
    public String stop(@NonNull final String id) {
        return operationWrapper(signatureHelper.getStopOperation(),
                (request) -> {
                    final JsonNode result = jsonHelper.toJson(request);
                    {
                        jsonHelper.put(result, "id", id);
                    }
                    return jsonHelper.toString(result);
                },
                this::ignore);
    }

    @IsAvailable("Shutdown")
    public String shutdown() {
        try {
            final JsonNode result = jsonHelper.getInstance();
            {
                jsonHelper.put(result, "message", String.format("shutdown after %d second", ShutdownHelper.TIMEOUT));
            }
            return jsonHelper.toString(result);
        } finally {
            shutdownHelper.asyncShutdown();
        }
    }

    private String operationWrapper(@NonNull final String operation,
                                    @NonNull final Function<String, String> prepareRequest,
                                    @NonNull final Function<String, String> prepareResponse) {
        final String requestData = prepareRequest.apply(operation);
        Objects.requireNonNull(requestData);

        final String responseData = requestHelper.request(requestData, requestHelper::asyncWrite, requestHelper::asyncRead);
        Objects.requireNonNull(responseData);

        final String result = prepareResponse.apply(responseData);
        Objects.requireNonNull(result);

        return result;
    }

    private String operationWrapper(@NonNull final String operation) {
        return operationWrapper(operation, this::ignore, this::ignore);
    }

    private String ignore(@NonNull final String data) {
        return data;
    }
}
