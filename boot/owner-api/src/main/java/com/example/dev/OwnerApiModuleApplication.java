package com.example.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OwnerApiModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwnerApiModuleApplication.class, args);
    }

}
