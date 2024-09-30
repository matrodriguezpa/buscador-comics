package com.matrodriguezpa.comicsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ComicSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComicSearchApplication.class, args);
    }
}
