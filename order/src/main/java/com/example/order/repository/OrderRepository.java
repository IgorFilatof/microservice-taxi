package com.example.order.repository;

import com.example.order.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Booking,Long> {
    @Query("select b from Booking b where b.orderStatus=com.example.order.model.OrderStatus.CREATED")
    List<Booking> getOrdersToPickup();

    List<Booking> getBookingByDriverId(String driverId);

    List<Booking> getBookingByCustomerUserId(String customerUserId);
}
