package com.perfumaria.controleestoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ControleEstoquePerfumariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                ControleEstoquePerfumariaApplication.class, args);
    }
}