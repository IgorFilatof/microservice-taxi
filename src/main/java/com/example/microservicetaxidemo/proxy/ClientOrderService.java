package com.example.microservicetaxidemo.proxy;

import com.example.microservicetaxidemo.dto.CustomerDto;
import com.example.microservicetaxidemo.dto.FillOrderDto;
import com.example.microservicetaxidemo.dto.OrderDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ClientOrderService {
    @RequestMapping(value = "/orders/{customerUserId}/list", method = RequestMethod.GET, produces = "application/json")
    List<OrderDto> getOrders(@PathVariable("customerUserId") String customerUserId);

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    void addCustomer(@RequestBody CustomerDto customerDto);

    @RequestMapping(value = "/orders/{customerUserId}/create", method = RequestMethod.POST,
            consumes = "application/json",  produces = "application/json")
    void createOrder(@PathVariable("customerUserId") String customerUserId,
                     @RequestBody FillOrderDto fillOrderDto);
}
