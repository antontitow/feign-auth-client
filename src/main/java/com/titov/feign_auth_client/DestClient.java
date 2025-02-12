package com.titov.feign_auth_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "destination", configuration = DestFeignClientConfig.class)
public interface DestClient {

    @GetMapping("test/{val}")
    String getThrough(@PathVariable String val);
}
