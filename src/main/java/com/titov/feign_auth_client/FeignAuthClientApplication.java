package com.titov.feign_auth_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class FeignAuthClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignAuthClientApplication.class, args);
    }

}
