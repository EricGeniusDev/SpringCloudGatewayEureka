package com.genius.tokenservice.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration("JwtProperty")
@ConfigurationProperties(prefix = "jwt.config")
@Data
@Primary
public class JwtProperty {
    private String loginUrl = "/login/**";
    private Long expiration = 3600L;
    private String privateKey = "VUM1hozhqwiKKJOoi5HyUlxjS9BQedZa";
    private String type = "encrypted";
    @NestedConfigurationProperty
    private Header header = new Header();

    @Data
    public static class Header {
        private String name = "";
        private String prefix = "";
    }
}
