package com.cobalt.config;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContainerConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Value("${cobalt.server.port}")
    private Integer cobaltServerPort;

    @Override
    public void customize(@NonNull final ConfigurableServletWebServerFactory container) {
        container.setPort(cobaltServerPort);
    }
}
