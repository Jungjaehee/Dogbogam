package com.dog.health.dogbogamserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DogbogamServerApplication {

    public static void main(String[] args) {
        System.out.println("health check started");
        SpringApplication.run(DogbogamServerApplication.class, args);
        System.out.println("health check finished");
    }

}
