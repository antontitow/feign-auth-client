package com.titov.feign_auth_client;

import com.titov.feign_auth_client.config.DestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class Util {
    private final DestClient destClient;
    private final RestTemplate restClient;

    //    @Transactional
    public void send() {
        log.info("Send by feign client");
        log.info(destClient.getThrough("feign"));
//        log.info(destClient.getThrough("val2"));
//        log.info("Send by rest client");
//        var restResult = restClient.getForEntity("http://localhost:8062/test/rest3", String.class);
//        var restResult = restClient.getForEntity("http://localhost:8085/destination/test/rest3", String.class);
//        log.info(restResult.getBody());
    }
}
