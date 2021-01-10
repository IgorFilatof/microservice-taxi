package com.example.driver.proxy;

import com.example.driver.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface DriverOrderService {
    @RequestMapping(value = "/drivers/add/{identifier}", method = RequestMethod.POST)
    void addDriver(@PathVariable("identifier") String identifier);

    @RequestMapping(value = "/orders/drivers/pickup-candidates", method = RequestMethod.GET)
    List<OrderDto> getOrdersToPickup();

    @RequestMapping(value = "/orders/drivers/driver-orders/{driverUserId}/list", method = RequestMethod.GET)
    List<OrderDto>  getDriverOrders(@PathVariable("driverUserId") String driverUserId);

    @RequestMapping(value = "/orders/driver/{driverUserId}/{orderId}/claim", method = RequestMethod.POST)
    ResponseEntity claimForOrder(@PathVariable("driverUserId") String driverUserId,
                                 @PathVariable("orderId") String orderId);

    @RequestMapping(value = "/orders/driver/{driverUserId}/{orderId}/start", method = RequestMethod.POST)
    ResponseEntity startOrderExecution(@PathVariable("driverUserId") String driverUserId,
                                       @PathVariable("orderId") String orderId);

    @RequestMapping(value = "/orders/driver/{driverUserId}/{orderId}/end", method = RequestMethod.POST)
    ResponseEntity endOrderExecution(@PathVariable("driverUserId") String driverUserId,
                                     @PathVariable("orderId") String orderId);
}
