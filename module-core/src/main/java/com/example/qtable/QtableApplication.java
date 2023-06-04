package com.example.qtable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class QtableApplication {

    public static void main(String[] args) {
        SpringApplication.run(QtableApplication.class, args);
    }

}
