package com.example.order.service;

import com.example.order.model.Booking;
import com.example.order.model.OrderStatus;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Booking claimOrder(String orderId, String driverUserId) {
        Optional<Booking> byId = orderRepository.findById(Long.parseLong(orderId));
        Booking order = byId.get();
        order.setDriverId(driverUserId);
        order.setOrderStatus(OrderStatus.ACCEPTED_BY_DRIVER);
        orderRepository.save(order);
        return order;
    }

    public Booking startOrderExecution(String orderId, String driverUserId) {
        Optional<Booking> byId = orderRepository.findById(Long.valueOf(orderId));
        Booking booking = byId.get();
        booking.setDriverId(driverUserId);
        booking.setOrderStatus(OrderStatus.IN_PROGRESS);
        orderRepository.save(booking);
        return booking;
    }

    public Booking endOrderExecution(String orderId, String driverUserId) {
        Optional<Booking> byId = orderRepository.findById(Long.valueOf(orderId));
        Booking booking = byId.get();
        booking.setDriverId(driverUserId);
        booking.setOrderStatus(OrderStatus.CLOSED);
        orderRepository.save(booking);
        return booking;
    }
}
