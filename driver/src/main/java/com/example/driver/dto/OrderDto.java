package com.example.driver.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDto implements Serializable {
    private Long id;
    private String customerUserId;
    private int statusOrder;
    private String driverId;
}
