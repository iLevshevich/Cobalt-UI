package com.cobalt.controllers;

import com.cobalt.helpers.JsonHelper;
import com.cobalt.helpers.OperationsHelper;
import com.cobalt.utils.HttpOperationsWrapper;
import com.cobalt.utils.HttpResponseCookieStatus;
import com.cobalt.utils.HttpResponseJsonStatus;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/management")
public class ManagementController implements HttpOperationsWrapper, HttpResponseJsonStatus, HttpResponseCookieStatus {
    private static final String cookiePath = "/";

    private final OperationsHelper operationsHelper;
    private final JsonHelper jsonHelper;

    @Autowired
    public ManagementController(@NonNull final OperationsHelper operationsHelper,
                                @NonNull final JsonHelper jsonHelper) {
        this.operationsHelper = operationsHelper;
        this.jsonHelper = jsonHelper;
    }

    @GetMapping(
            path = "/shutdown",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String shutdown(@NonNull final HttpServletResponse response) {
        return operationsWrapper(
                operationsHelper::shutdown,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "ManagementController", "shutdown")
        );
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public JsonHelper getJsonHelper() {
        return jsonHelper;
    }
}