package com.titov.feign_auth_client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final DestClient destClient;

    @Scheduled(fixedDelay = 17000)
    public void sendToDestination() {
        log.info("schedule pull/{val}");
        try {
            log.info(destClient.getThrough("val"));
        }catch (Exception e){
            log.error("schedule send Error + " + e.getMessage());
            log.error(e.getStackTrace().toString());
        }
    }
}
