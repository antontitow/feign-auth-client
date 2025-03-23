package com.titov.feign_auth_client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestConfig {

    @Bean
    public RestTemplate restClient(RestTemplateBuilder builder) {
        return builder
                .build();
    }
}
