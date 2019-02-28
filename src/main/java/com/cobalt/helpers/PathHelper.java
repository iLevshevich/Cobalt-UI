package com.cobalt.helpers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PathHelper {
    @Getter
    @Value("${cobalt.security.location}")
    private String cobaltSecurityLocation;

    @Getter
    @Value("${cobalt.properties.location}")
    private String cobaltPropertiesLocation;
}
