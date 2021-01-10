package com.example.microservicetaxidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceTaxiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceTaxiDemoApplication.class, args);
    }

}
