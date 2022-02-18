package com.genius.webfluxfuncional.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("email")
@Getter
@Setter
public class EmailProperties {
    private String hostname;
    private int port;
    private String from;
}
