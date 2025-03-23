package com.titov.feign_auth_client.monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class MetricScheduler {

    private final AtomicInteger testGauge;
    private final Counter testCounter;

    public MetricScheduler(MeterRegistry meterRegistry) {
        // Counter vs. gauge, summary vs. histogram
        // https://prometheus.io/docs/practices/instrumentation/#counter-vs-gauge-summary-vs-histogram
        testGauge = meterRegistry.gauge("feign_custom_gauge", new AtomicInteger(0));
        testCounter = meterRegistry.counter("feign_custom_counter");
    }

    @Scheduled(fixedRateString = "25000", initialDelayString = "0")
    public void schedulingTask() {
        testGauge.set(MetricScheduler.getRandomNumberInRange(0, 100));
        log.info("log GAUGE: {}", testGauge.get());
        testCounter.increment();
        log.info("log COUNTER: {}", testCounter.count());
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}