package com.cobalt.helpers;

import com.cobalt.misc.Pair;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
@Component
public class OperationSignatureHelper {
    private final JsonHelper jsonHelper;

    @Value("${cobalt.client.id}")
    private String cobaltClientId;

    @Autowired
    public OperationSignatureHelper(@NonNull final JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    private String typesOperation = "";
    private String statusesOperation = "";
    private String cleanLastErrorsOperation = "";
    private String saveConfigurationOperation = "";
    private String startAllOperation = "";
    private String abortAllOperation = "";
    private String resetAllOperation = "";
    private String stopAllOperation = "";
    private String addOperation = "";
    private String removeOperation = "";
    private String startOperation = "";
    private String abortOperation = "";
    private String resetOperation = "";
    private String stopOperation = "";

    @PostConstruct
    private void init() {
        this.typesOperation = getOperationSignature("GetDispatcherTypes");
        this.statusesOperation = getOperationSignature("GetStatuses");
        this.cleanLastErrorsOperation = getOperationSignature("CleanLastErrors");
        this.saveConfigurationOperation = getOperationSignature("SaveConfiguration");
        this.startAllOperation = getOperationSignature("StartDispatchers");
        this.abortAllOperation = getOperationSignature("AbortDispatchers");
        this.resetAllOperation = getOperationSignature("ResetDispatchers");
        this.stopAllOperation = getOperationSignature("StopDispatchers");
        this.addOperation = getOperationSignature("AddDispatcher");
        this.removeOperation = getOperationSignature("RemoveDispatcher");
        this.startOperation = getOperationSignature("StartDispatcher");
        this.abortOperation = getOperationSignature("AbortDispatcher");
        this.resetOperation = getOperationSignature("ResetDispatcher");
        this.stopOperation = getOperationSignature("StopDispatcher");
    }

    private String getOperationSignature(@NonNull final String name) {
        final JsonNode result = jsonHelper.getInstance(Pair.of("client", cobaltClientId), Pair.of("operation", name));
        return jsonHelper.toString(result);
    }
}
