package com.cobalt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("${cobalt.properties.location}")
public class PropertiesConfig {
}
