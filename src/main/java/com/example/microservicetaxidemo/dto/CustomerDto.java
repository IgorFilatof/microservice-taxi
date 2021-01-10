package com.example.microservicetaxidemo.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String phone;
    private String uuid;
}
