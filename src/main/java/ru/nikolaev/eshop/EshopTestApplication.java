package ru.nikolaev.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.nikolaev.eshop")
public class EshopTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopTestApplication.class, args);
    }

}
