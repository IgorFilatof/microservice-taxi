package com.example.order.controller;

import com.example.order.converter.BookingToOrderDtoConvertor;
import com.example.order.dto.FillOrderDto;
import com.example.order.dto.OrderDto;
import com.example.order.model.Booking;
import com.example.order.model.FillOrder;
import com.example.order.model.OrderStatus;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookingToOrderDtoConvertor bookingToOrderDtoConvertor;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders/getAllOrders", method = RequestMethod.GET)
    public List<OrderDto> getAllOrders() {
        List<Booking> ordersToPickup = orderRepository.findAll();
        List<OrderDto> collect = ordersToPickup.stream()
                .map(booking -> bookingToOrderDtoConvertor.apply(booking))
                .collect(Collectors.toList());
        return collect;
    }

    @RequestMapping(value = "/orders/drivers/pickup-candidates", method = RequestMethod.GET)
    public List<OrderDto> getOrdersToPickup() {
        List<Booking> ordersToPickup = orderRepository.getOrdersToPickup();
        List<OrderDto> collect = ordersToPickup.stream()
                .map(booking -> bookingToOrderDtoConvertor.apply(booking))
                .collect(Collectors.toList());
        return collect;
    }

    @RequestMapping(value = "/orders/drivers/driver-orders/{driverUserId}/list", method = RequestMethod.GET)
    public List<OrderDto> getDriverOrders(@PathVariable String driverUserId) {
        List<Booking> bookingByDriverId = orderRepository.getBookingByDriverId(driverUserId);
        List<OrderDto> listOrderDto = bookingByDriverId.stream()
                .map(booking -> bookingToOrderDtoConvertor.apply(booking))
                .collect(Collectors.toList());
        return listOrderDto;
    }

    @RequestMapping(value = "/orders/driver/{driverUserId}/{orderId}/claim", method = RequestMethod.POST)
    public ResponseEntity claimOrder(@PathVariable String driverUserId, @PathVariable String orderId) {
        Booking order = orderService.claimOrder(driverUserId, orderId);
        return new ResponseEntity(order, HttpStatus.ACCEPTED);
    }

    public @RequestMapping(value = "/orders/driver/{driverUserId}/{orderId}/start", method = RequestMethod.POST)
    ResponseEntity startOrderExecution(@PathVariable("driverUserId") String driverUserId,
                                       @PathVariable("orderId") String orderId) {
        Booking booking = orderService.startOrderExecution(driverUserId, orderId);
        return new ResponseEntity(booking, HttpStatus.OK);
    }

    public @RequestMapping(value = "/orders/driver/{driverUserId}/{orderId}/end", method = RequestMethod.POST)
    ResponseEntity endOrderExecution(@PathVariable("driverUserId") String driverUserId,
                                     @PathVariable("orderId") String orderId) {
        Booking booking = orderService.endOrderExecution(driverUserId, orderId);
        return new ResponseEntity(booking, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{customerUserId}/list", method = RequestMethod.GET)
    List<OrderDto> getOrders(@PathVariable("customerUserId") String customerUserId) {
        List<Booking> bookingByCustomerUserId = orderRepository.getBookingByCustomerUserId(customerUserId);
        List<OrderDto> listOrderDto = bookingByCustomerUserId.stream()
                .map(booking -> bookingToOrderDtoConvertor.apply(booking))
                .collect(Collectors.toList());
        return listOrderDto;
    }

    @RequestMapping(value = "/orders/{customerUserId}/create", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    void createOrder(@PathVariable("customerUserId") String customerUserId,
                     @RequestBody FillOrderDto fillOrderDto) {
        Booking booking = new Booking();
        booking.setOrderStatus(OrderStatus.NEW);
        booking.setCustomerUserId(customerUserId);
        booking.setAddress(fillOrderDto.getAddress());
        booking.setPhone(fillOrderDto.getPhone());

        orderRepository.save(booking);
    }
}
