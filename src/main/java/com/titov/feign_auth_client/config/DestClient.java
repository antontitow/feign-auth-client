package com.titov.feign_auth_client.config;

import com.titov.feign_auth_client.config.auth.DestFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "destination", configuration = DestFeignClientConfig.class)
@FeignClient(name = "keycloak", url = "http://localhost:8085/destination", configuration = DestFeignClientConfig.class)
//@FeignClient(name = "keycloak", url = "http://localhost:8062/", configuration = DestFeignClientConfig.class)
public interface DestClient {

    @GetMapping("test/{val}")
    String getThrough(@PathVariable String val);
}
