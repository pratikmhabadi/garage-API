package com.garage.garage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enable", matchIfMissing = true)
public class GarageApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarageApplication.class, args);
    }

}

