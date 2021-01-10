package com.example.driver.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "Driver-client",url = "localhost:8200")
public interface DriverOrderClient extends DriverOrderService {
}
