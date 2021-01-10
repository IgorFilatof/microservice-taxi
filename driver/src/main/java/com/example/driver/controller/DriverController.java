package com.example.driver.controller;

import com.example.driver.dto.OrderDto;
import com.example.driver.model.Driver;
import com.example.driver.proxy.DriverOrderClient;
import com.example.driver.proxy.DriverOrderService;
import com.example.driver.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DriverController implements DriverOrderService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverOrderClient driverOrderClient;

    @GetMapping("/allDriver")
    public List<Driver> getAllClient() {
        return driverRepository.findAll();
    }

    @Override
    public void addDriver(String identifier) {
        Driver driver = new Driver();
        driver.setUuid(identifier);
        driverRepository.save(driver);
    }

    @Override
    @RequestMapping(value = "/driver/getFreeOrders", method = RequestMethod.GET)
    public List<OrderDto> getOrdersToPickup() {
        return driverOrderClient.getOrdersToPickup();
    }

    @Override
    @RequestMapping(value = "/driver/getDriverOrder/{driverUserId}", method = RequestMethod.GET)
    public List<OrderDto> getDriverOrders(@PathVariable String driverUserId) {
        return driverOrderClient.getDriverOrders(driverUserId);
    }

    @Override
    @RequestMapping(value = "/driver/claimOrder/{driverUserId}/{orderId}", method = RequestMethod.POST)
    public ResponseEntity claimForOrder(@PathVariable String driverUserId, @PathVariable String orderId) {

        ResponseEntity responseEntity = driverOrderClient.claimForOrder(driverUserId, orderId);
        return responseEntity;
    }

    @Override
    @RequestMapping(value = "/driver/startOrder/{driverUserId}/{orderId}", method = RequestMethod.POST)
    public ResponseEntity startOrderExecution(@PathVariable String driverUserId, @PathVariable String orderId) {
        ResponseEntity responseEntity = driverOrderClient.startOrderExecution(driverUserId, orderId);
        return responseEntity;
    }

    @Override
    @RequestMapping(value = "/driver/endOrder/{driverUserId}/{orderId}", method = RequestMethod.POST)
    public ResponseEntity endOrderExecution(@PathVariable String driverUserId, @PathVariable String orderId) {
        ResponseEntity responseEntity = driverOrderClient.endOrderExecution(driverUserId, orderId);
        return responseEntity;
    }
}
