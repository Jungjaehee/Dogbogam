package com.dog.health.dogbogamserver.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration(proxyBeanMethods = false)
public class TimeConfig {
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
