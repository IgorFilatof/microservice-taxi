package com.example.order.converter;

import com.example.order.dto.OrderDto;
import com.example.order.model.Booking;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookingToOrderDtoConvertor implements Function<Booking,OrderDto> {

    @Override
    public OrderDto apply(Booking booking) {
        OrderDto orderDto=new OrderDto();

        orderDto.setId(booking.getId());
        orderDto.setCustomerUserId(booking.getCustomerUserId());
        orderDto.setStatusOrder(booking.getOrderStatus().getValue());
        orderDto.setDriverId(booking.getDriverId());
        return orderDto;
    }



}
