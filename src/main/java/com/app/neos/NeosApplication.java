package com.app.neos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NeosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeosApplication.class, args);
    }

}
