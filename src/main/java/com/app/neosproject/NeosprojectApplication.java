package com.app.neosproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NeosprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeosprojectApplication.class, args);
    }

}
