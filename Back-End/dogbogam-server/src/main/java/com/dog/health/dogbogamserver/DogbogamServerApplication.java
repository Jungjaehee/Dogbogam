package com.dog.health.dogbogamserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DogbogamServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogbogamServerApplication.class, args);
    }

}
