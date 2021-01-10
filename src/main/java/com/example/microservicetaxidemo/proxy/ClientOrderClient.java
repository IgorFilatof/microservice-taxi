package com.example.microservicetaxidemo.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "Client-client",url = "localhost:8200")
public interface ClientOrderClient extends ClientOrderService{
}
