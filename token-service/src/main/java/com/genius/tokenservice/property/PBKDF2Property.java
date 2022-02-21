package com.genius.tokenservice.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "pbkdf2.config")
@Getter
@Setter
public class PBKDF2Property {
    private String secret = "VUM1hozhqwiKKJOoi5HyUlxjS9BQedZa";
    private Integer iterations = 3000;
    private Integer keylength = 33;
}
