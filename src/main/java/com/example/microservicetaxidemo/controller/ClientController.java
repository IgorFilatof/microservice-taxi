package com.example.microservicetaxidemo.controller;

import com.example.microservicetaxidemo.converter.ClientToClientDtoConverter;
import com.example.microservicetaxidemo.dto.FillOrderDto;
import com.example.microservicetaxidemo.dto.OrderDto;
import com.example.microservicetaxidemo.proxy.ClientOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientOrderClient clientOrderClient;

    @Autowired
    private ClientToClientDtoConverter clientToClientDtoConverter;

    @RequestMapping(value = "client/getCustomerOrders/{customerUserId}",method = RequestMethod.GET)
    public List<OrderDto> getOrders(@PathVariable("customerUserId") String customerUserId) {
        List<OrderDto> orders = clientOrderClient.getOrders(customerUserId);
        return orders;
    }

    @RequestMapping(value = "/client/createOrder/{customerUserId}", method = RequestMethod.POST)
    void createOrder(@PathVariable("customerUserId") String customerUserId,
                     @RequestBody FillOrderDto fillOrderDto){
        clientOrderClient.createOrder(customerUserId,fillOrderDto);
    }
}
