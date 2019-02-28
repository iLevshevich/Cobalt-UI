package com.cobalt.model;

import com.cobalt.annotations.RequestEntityValidate;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(of = {"id", "description"})
@RequestEntityValidate
public class RequestEntity {
    @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")
    private String id;

    @Size(min = 3, max = 100)
    private String description;

    private String type;

    @Pattern(regexp = "(((0|1)?[0-9][0-9]?|2[0-4][0-9]|25[0-5])[.]){3}((0|1)?[0-9][0-9]?|2[0-4][0-9]|25[0-5])")
    private String inputHost;

    @Digits(integer = 5, fraction = 0)
    private String inputPort;

    private String inputUser;

    private String inputPassword;

    private String inputVhost;

    private String inputPrefetch;

    private String inputExchange;

    private String inputQueue;

    private String inputRoutingKey;

    @Pattern(regexp = "(((0|1)?[0-9][0-9]?|2[0-4][0-9]|25[0-5])[.]){3}((0|1)?[0-9][0-9]?|2[0-4][0-9]|25[0-5])")
    private String outputHost;

    @Digits(integer = 5, fraction = 0)
    private String outputPort;

    private String outputUser;

    private String outputPassword;

    private String outputVhost;

    private String outputPrefetch;

    private String outputExchange;

    private String outputQueue;

    private String outputRoutingKey;
}
