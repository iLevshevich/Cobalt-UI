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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/cobalt/v{version:1.[\\d]+}")
public class DefaultController implements HttpOperationsWrapper, HttpResponseJsonStatus, HttpResponseCookieStatus {
    private static final String cookiePath = "/";

    private final OperationsHelper operationsHelper;
    private final JsonHelper jsonHelper;

    @Autowired
    public DefaultController(@NonNull final OperationsHelper operationsHelper,
                             @NonNull final JsonHelper jsonHelper) {
        this.operationsHelper = operationsHelper;
        this.jsonHelper = jsonHelper;
    }

    @GetMapping(
            path = "/grants",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getGrants(@NonNull final HttpServletResponse response) {
        return operationsWrapper(
                operationsHelper::getGrants,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "getGrants")
        );
    }

    @GetMapping(
            path = "/statuses",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getStatuses(@NonNull final HttpServletResponse response) {
        return operationsWrapper(
                operationsHelper::getStatuses,
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "getStatuses")
        );
    }

    @GetMapping(
            path = "/types",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getTypes(@NonNull final HttpServletResponse response) {
        return operationsWrapper(
                operationsHelper::getTypes,
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "getTypes")
        );
    }

    @PutMapping(
            path = "/clean",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String cleanLastErrors(@NonNull final HttpServletResponse response) {
        return operationsWrapper(
                operationsHelper::cleanLastErrors,
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "cleanLastErrors")
        );
    }

    @PutMapping(
            path = "/save",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String saveConfiguration(@NonNull final HttpServletResponse response) {
        return operationsWrapper(
                operationsHelper::saveConfiguration,
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "saveConfiguration")
        );
    }

    @PutMapping(
            path = "/start",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String startAll(@NonNull final HttpServletResponse response) {
        return operationsWrapper(
                operationsHelper::startAll,
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "startAll")
        );
    }

    @PutMapping(
            path = "/abort",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String abortAll(@NonNull final HttpServletResponse response) {
        return operationsWrapper(
                operationsHelper::abortAll,
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "abortAll")
        );
    }

    @PutMapping(
            path = "/reset",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String resetAll(@NonNull final HttpServletResponse response) {
        return operationsWrapper(
                operationsHelper::resetAll,
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "resetAll")
        );
    }

    @PutMapping(
            path = "/stop",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String stopAll(@NonNull final HttpServletResponse response) {
        return operationsWrapper(
                operationsHelper::stopAll,
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "stopAll")
        );
    }

    @PostMapping(
            path = "/add",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String add(@NonNull @RequestBody final Map<String, Object> requestMap,
                      @NonNull final HttpServletResponse response) {
        return operationsWrapper(
                () -> operationsHelper.add(requestMap),
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "add")
        );
    }

    @DeleteMapping(
            path = "/remove/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String remove(@NonNull final HttpServletResponse response,
                         @NonNull @PathVariable("id") final String id) {
        return operationsWrapper(
                () -> operationsHelper.remove(id),
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "remove")
        );
    }

    @PutMapping(
            path = "/start/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String start(@NonNull final HttpServletResponse response,
                        @NonNull @PathVariable("id") final String id) {
        return operationsWrapper(
                () -> operationsHelper.start(id),
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "start")
        );
    }

    @PutMapping(
            path = "/abort/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String abort(@NonNull final HttpServletResponse response,
                        @NonNull @PathVariable("id") final String id) {
        return operationsWrapper(
                () -> operationsHelper.abort(id),
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "abort")
        );
    }

    @PutMapping(
            path = "/reset/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String reset(@NonNull final HttpServletResponse response,
                        @NonNull @PathVariable("id") final String id) {
        return operationsWrapper(
                () -> operationsHelper.reset(id),
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "reset")
        );
    }

    @PutMapping(
            path = "/stop/{id:[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String stop(@NonNull final HttpServletResponse response,
                       @NonNull @PathVariable("id") final String id) {
        return operationsWrapper(
                () -> operationsHelper.stop(id),
                this::checkSuccessJsonStatus,
                (result) -> {
                    addSuccessCookieStatus(response, cookiePath);
                    return addSuccessJsonStatus(result);
                },
                (result) -> {
                    addFailedCookieStatus(response, cookiePath);
                    return addFailedJsonStatus(result);
                },
                () -> String.format("%s::%s: ", "DefaultController", "stop")
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
