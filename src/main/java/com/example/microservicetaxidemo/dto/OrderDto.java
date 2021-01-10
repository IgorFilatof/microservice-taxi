package com.example.microservicetaxidemo.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String customerUserId;
    private int statusOrder;
    private String driverId;
}
