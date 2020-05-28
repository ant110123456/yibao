package com.yb.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Data
public class PayConfig {
    @Value("${key}")
    private String key;
    @Value("${p1MerId}")
    private String p1_MerId;
    @Value("${keyValue}")
    private String keyValue;
    @Value("${merchantCallbackURL}")
    private String merchantCallbackURL;
}
